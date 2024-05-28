package com.enesbaskaya.mybank.data.api

import android.content.Context
import android.util.Log
import com.enesbaskaya.mybank.R
import com.enesbaskaya.mybank.data.repository.ApiRepository
import com.google.gson.*
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.net.ssl.SSLException


class ApiHelper @Inject constructor(context: Context, apiService: ApiService) :
    ApiRepository {
    var mGsonBuilder: GsonBuilder

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MyEntryPoint {
        fun getGsonBuilder(): GsonBuilder
    }


    private var apiService: ApiService
    private var mContext: Context


    init {
        this.apiService = apiService
        this.mContext = context
        val myEntryPoint = EntryPointAccessors.fromApplication(context, MyEntryPoint::class.java)
        mGsonBuilder = myEntryPoint.getGsonBuilder()
    }


    fun <T> toResultFlow(call: suspend () -> Response<T>?): Flow<ApiResult<T>?> {
        return flow {
            try {
                emit(ApiResult.Loading())
                val c = call()
                if (c?.isSuccessful == true) {
                    logForResponseAndRequest(c)
                    if (c.body() != null) {

                        var data = c.body()
                        emit(ApiResult.Success(data))


                    } else {

                        var message = mContext.getString(R.string.message_unknown_error)
                        emit(ApiResult.Error(message))
                    }


                } else {
                    c?.errorBody()?.let {
                        val error = it.string()
                        it.close()
                        emit(ApiResult.Error(error))
                    }
                }
            } catch (e: Exception) {
                var mError = when (e) {
                    is CancellationException -> e
                    is UnknownHostException -> RetrofitException(
                        mContext.getString(R.string.message_no_internet_connection),
                        null,
                        e
                    )

                    is TimeoutException,
                    is IOException,
                    is SSLException,
                    -> NetworkException(e)

                    is ConnectException -> InternetConnectionException(e)
                    is HttpException -> RetrofitException(
                        mContext.getString(R.string.message_no_internet_connection),
                        e.response(),
                        e
                    )

                    else -> UnknownNetworkException(e)

                }

                emit(ApiResult.Error(mError.message))

            }
        }.flowOn(Dispatchers.IO)
    }

    private fun <T> logForResponseAndRequest(c: Response<T>?) {
        try {
            val gson = getGson()
            val jsonResponse = gson.toJson(c?.body())
            val jsonRequest = gson.toJson(c?.raw()?.request)
            Log.e("MY_JSON", "********************************")
            Log.e("MY_JSON", "Request= $jsonRequest")
            Log.e("MY_JSON", "-------------------------------")
            Log.e("MY_JSON", "Response= $jsonResponse")
            Log.e("MY_JSON", "******************************")

        } catch (e: Exception) {
            Log.e("MY_JSON", "************ERROR******************")

        }
    }


    private fun getGson(): Gson {


        return mGsonBuilder.create()

    }

    override suspend fun getBankList() = toResultFlow {
        apiService.getBankList()
    }


}