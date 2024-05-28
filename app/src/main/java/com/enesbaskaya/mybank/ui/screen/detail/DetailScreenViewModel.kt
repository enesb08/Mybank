package com.enesbaskaya.mybank.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.enesbaskaya.mybank.data.model.BankDetail
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : ViewModel() {



    fun logOpenedDetailScreenEvent(bankDetail: BankDetail) {
        firebaseAnalytics.logEvent("opened_detail_screen") {
            param("bankBranch", bankDetail.bankBranch)
            param("id", bankDetail.id.toString())
            param("city", bankDetail.city)
            param("addressName", bankDetail.addressName)

        }
    }


}

