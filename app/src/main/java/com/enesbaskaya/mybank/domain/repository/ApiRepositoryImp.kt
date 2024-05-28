package com.enesbaskaya.mybank.domain.repository

import com.enesbaskaya.mybank.data.api.ApiHelper
import com.enesbaskaya.mybank.data.api.ApiResult
import com.enesbaskaya.mybank.data.model.BankDetail
import com.enesbaskaya.mybank.data.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiRepositoryImp @Inject constructor(
    apiHelper: ApiHelper,
) :
    ApiRepository {

    private var apiHelper: ApiHelper


    init {
        this.apiHelper = apiHelper

    }

    override suspend fun getBankList(): Flow<ApiResult<List<BankDetail>>?> {
        return apiHelper.getBankList()
    }

}