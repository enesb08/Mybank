package com.enesbaskaya.mybank

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import com.enesbaskaya.mybank.util.LangContextWrapper
import com.enesbaskaya.mybank.util.LocalDataManager
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale
import javax.inject.Inject


@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var localDataManager: LocalDataManager

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(updateBaseContextLocale(base))
    }

    private fun updateBaseContextLocale(context: Context): Context {
        val pref = context.getSharedPreferences("com.enesbaskaya.mybank", Context.MODE_PRIVATE)
        val lang = pref?.getString(LocalDataManager.KEY_LANGUAGE_CODE, "en").toString()
        val locale = Locale(lang)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val configuration: Configuration = context.resources.configuration
            configuration.setLocale(locale)
            return context.createConfigurationContext(configuration)
        } else {
            val configuration: Configuration = context.resources.configuration
            configuration.setLocale(locale)
            return context.createConfigurationContext(configuration)
        }
    }

}
