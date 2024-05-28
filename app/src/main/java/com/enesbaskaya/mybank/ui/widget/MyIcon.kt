package com.enesbaskaya.mybank.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enesbaskaya.mybank.R

@Composable
fun MyIcon(resBgColor: Int, resIcon: Int) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(1.dp, Color.White, CircleShape)
            .padding(1.dp)
            .clip(CircleShape)
            .background(colorResource(id = resBgColor))
            .size(18.dp)

    ) {

        Icon(
            modifier = Modifier.size(10.dp),
            painter = painterResource(id = resIcon),
            tint = colorResource(id = R.color.soft_gray),
            contentDescription = ""
        )
    }

}

@Preview(showBackground = true)
@Composable
fun MyIconPreview() {
    MyIcon(
        resIcon = R.drawable.ic_bank,
        resBgColor = R.color.purple_700
    )
}