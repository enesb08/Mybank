package com.enesbaskaya.mybank.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesbaskaya.mybank.R

@Composable
fun ErrorPage(
    modifier: Modifier = Modifier,
    message: String,
    errorIcon: Int,
    btnText: String,
    onClicked: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                modifier = modifier
                    .size(50.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = errorIcon),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(10.dp))

            Text(text = message, fontSize = 13.sp, color = Color.Black)

            Spacer(modifier = Modifier.size(40.dp))
            OutlinedButton(
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .height(40.dp),
                onClick = { onClicked.invoke() },
                border = BorderStroke(1.dp, Color.Red),
                shape = RoundedCornerShape(30), // = 50% percent
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
            ) {
                Text(text = btnText)
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ErrorPagePreview() {
    val message = "Bilinmeyen bir  hata oluştur"
    ErrorPage(
        message = message,
        errorIcon = R.drawable.ic_error,
        btnText = "Tekrar Dene",
        onClicked = {})
}