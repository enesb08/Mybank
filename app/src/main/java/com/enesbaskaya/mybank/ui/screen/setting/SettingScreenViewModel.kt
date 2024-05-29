package com.enesbaskaya.mybank.ui.screen.setting

import androidx.lifecycle.ViewModel
import com.enesbaskaya.mybank.util.LocalDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(
    private val localDataManager: LocalDataManager
) : ViewModel() {
    fun changeLanguage(it: Languages, restartApp: () -> Unit) {
        val currentLang = localDataManager.getLanguage()
        if (currentLang != it.key) {
            localDataManager.setLanguage(it.key)
            restartApp()
        }


    }


}

