package com.enesbaskaya.mybank.ui.screen.detail


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enesbaskaya.mybank.data.model.BankDetail
import com.enesbaskaya.mybank.util.openGoogleMapWithAddress
import com.google.gson.Gson
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun DetailScreen(
    navController: NavController,
    bankDetailJson: String,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {

    val gson = Gson()
    val bankDataJson = URLDecoder.decode(bankDetailJson, StandardCharsets.UTF_8.toString())
    val bankData = gson.fromJson(bankDataJson, BankDetail::class.java)

    LaunchedEffect(Unit) {
        viewModel.logOpenedDetailScreenEvent(bankData)

    }

    val context = LocalContext.current

    DetailContent(bankDetail = bankData, onBackClick = {
        navController.popBackStack()
    }, onGoAddressClick = {

        context.openGoogleMapWithAddress(bankData.addressDetail)

    })

}



