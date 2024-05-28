package com.enesbaskaya.mybank.data.repository

import com.enesbaskaya.mybank.data.api.ApiResult
import com.enesbaskaya.mybank.data.model.BankDetail
import kotlinx.coroutines.flow.Flow

interface ApiRepository {


    suspend fun getBankList(): Flow<ApiResult<List<BankDetail>>?>


}