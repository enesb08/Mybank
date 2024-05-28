package com.enesbaskaya.mybank.data.api

import com.enesbaskaya.mybank.data.model.BankDetail
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("bankdata")
    suspend fun getBankList(): Response<List<BankDetail>>
}