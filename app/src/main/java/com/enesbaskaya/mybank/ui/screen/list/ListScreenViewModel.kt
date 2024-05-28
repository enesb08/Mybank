package com.enesbaskaya.mybank.ui.screen.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesbaskaya.mybank.data.api.ApiResult
import com.enesbaskaya.mybank.data.model.BankDetail
import com.enesbaskaya.mybank.domain.usecase.BankListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val bankListUseCase: BankListUseCase,
) : ViewModel() {

    private val _ListScreenState = mutableStateOf<ListScreenState>(ListScreenState.Init)
    val listScreenState = _ListScreenState
    private var bankDataList: ArrayList<BankDetail> = arrayListOf()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    init {
        getBankList()
    }

    fun getBankList() = viewModelScope.launch {
        bankListUseCase.invoke().collect {
            when (it) {
                is ApiResult.Error -> {
                    if (it.message != null) {
                        _ListScreenState.value = ListScreenState.Error(it.message)

                    }
                }

                is ApiResult.Loading -> {
                    _ListScreenState.value = ListScreenState.Content(null, true)

                }

                is ApiResult.Success -> {
                    //plugin to show loading design
                    delay(1000)
                    if (it.response != null) {
                        bankDataList.clear()
                        bankDataList.addAll(it.response)
                        _ListScreenState.value = ListScreenState.Content(it.response, false)

                    }

                }

                else -> Unit
            }
        }
    }

    fun filterBankList(query: String) {
        _searchText.value = query
        val filteredList = bankDataList.filter { it.city.contains(query, ignoreCase = true) }
        _ListScreenState.value = ListScreenState.Content(filteredList, false)


    }


}

sealed class ListScreenState {
    data object Init : ListScreenState()
    class Content(val data: List<BankDetail>? = null, val isLoading: Boolean = false) :
        ListScreenState()

    class Error(val errorMessage: String) : ListScreenState()

}

