package com.enesbaskaya.mybank

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.enesbaskaya.mybank.ui.screen.navigation.SetUpNavigation
import com.enesbaskaya.mybank.ui.theme.MyBankTheme
import com.enesbaskaya.mybank.util.LocalDataManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBankTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetUpNavigation()


                }
            }
        }
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyBankTheme {
        Greeting("Android")
    }
}