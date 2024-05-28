package com.enesbaskaya.mybank.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.enesbaskaya.mybank.R
import com.enesbaskaya.mybank.ui.screen.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(Screen.ListScreen.route){
            popUpTo(Screen.SplashScreen.route) {
                inclusive = true
            }
        }
    }
    SplashContent()

}

@Composable
fun SplashContent(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painterResource(R.drawable.ic_splash_logo),
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
    }

}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashContent()
}