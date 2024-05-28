package com.enesbaskaya.mybank.ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enesbaskaya.mybank.R
import com.enesbaskaya.mybank.data.model.BankDetail
import com.enesbaskaya.mybank.ui.screen.list.item.BankItem
import com.enesbaskaya.mybank.ui.widget.CustomSearchView
import com.enesbaskaya.mybank.ui.widget.EmptyPage
import com.enesbaskaya.mybank.ui.widget.ListAppBar
import com.enesbaskaya.mybank.ui.widget.LoadingPage


@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    bankList: List<BankDetail>?,
    isLoading: Boolean,
    searchText: String,
    onValueChange: (String) -> Unit,
    navigateToDetail: (BankDetail) -> Unit

) {

    ListAppBar(title = stringResource(id = R.string.title_banks)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
        ) {

            Column(modifier = Modifier.fillMaxSize()) {
                CustomSearchView(search = searchText, onValueChange = onValueChange)
                if (isLoading) {
                    LoadingPage()
                } else {
                    if (bankList != null) {
                        if (bankList.isNotEmpty()) {

                            LazyColumn(
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                items(count = bankList.size,
                                    key = {
                                        bankList[it].id
                                    },
                                    itemContent = { index ->
                                        val bankItem = bankList[index]

                                        BankItem(bankItem, navigateToDetail = navigateToDetail)
                                    })

                            }


                        } else {
                            EmptyBankList()

                        }
                    }

                }
            }


        }
    }


}

@Composable
private fun EmptyBankList() {
    val message = stringResource(id = R.string.message_bank_list_not_found)
    EmptyPage(
        message = message,
        errorIcon = R.drawable.ic_empty_content,
    )
}


@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListContent(
        bankList = arrayListOf(),
        isLoading = false,
        searchText = "",
        onValueChange = {}
    ) {}
}

