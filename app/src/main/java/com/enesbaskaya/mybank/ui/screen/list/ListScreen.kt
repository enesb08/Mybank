package com.enesbaskaya.mybank.ui.screen.list


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enesbaskaya.mybank.R
import com.enesbaskaya.mybank.ui.screen.Screen
import com.enesbaskaya.mybank.ui.widget.ErrorPage
import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun ListScreen(navController: NavController, viewModel: ListScreenViewModel = hiltViewModel()) {

    val search by viewModel.searchText.collectAsState()

    val gson = Gson()
    when (val state = viewModel.listScreenState.value) {

        is ListScreenState.Content -> {
            val isLoading = state.isLoading
            val bankDetails = state.data

            ListContent(
                bankList = bankDetails,
                isLoading = isLoading,
                searchText = search,
                onValueChange = {
                    viewModel.filterBankList(it)

                }) {
                val bankDataJson = gson.toJson(it)
                val encodedBankDataJson =
                    URLEncoder.encode(bankDataJson, StandardCharsets.UTF_8.toString())
                navController.navigate("${Screen.DetailScreen.route}/$encodedBankDataJson")

            }
        }

        is ListScreenState.Error -> {
            val errorMessage = state.errorMessage
            ErrorPage(
                message = errorMessage,
                errorIcon = R.drawable.ic_error,
                btnText = stringResource(id = R.string.btn_try_again),
                onClicked = {
                    viewModel.getBankList()
                })
        }

        else -> Unit
    }


}

