package com.enesbaskaya.mybank.ui.screen.setting

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesbaskaya.mybank.R
import com.enesbaskaya.mybank.ui.widget.AppBarWithBackButton

@Composable
fun SettingContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onChangeLanguageClick: () -> Unit,
) {

    AppBarWithBackButton(
        title = stringResource(id = R.string.title_setting),
        onBackClick = onBackClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
        ) {
            Card(
                modifier = Modifier
                    .clickable { onChangeLanguageClick.invoke() }
                    .padding(horizontal = 6.dp, vertical = 6.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                border = BorderStroke(0.5.dp, colorResource(id = R.color.item_blue)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(corner = CornerSize(12.dp)),
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.tv_change_language),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }


            }

        }
    }
}
