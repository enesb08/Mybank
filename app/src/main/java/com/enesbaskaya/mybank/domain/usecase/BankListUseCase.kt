package com.enesbaskaya.mybank.domain.usecase

import com.enesbaskaya.mybank.data.repository.ApiRepository
import javax.inject.Inject

class BankListUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend operator fun invoke() = apiRepository.getBankList()
}