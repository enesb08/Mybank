package com.enesbaskaya.mybank.ui.screen.list.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesbaskaya.mybank.R
import com.enesbaskaya.mybank.data.model.BankDetail

@Composable
fun BankItem(bankDetail: BankDetail, navigateToDetail: (BankDetail) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .fillMaxWidth(),
        border = BorderStroke(0.5.dp, colorResource(id = R.color.item_blue)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
    ) {
        Row(
            Modifier.clickable { navigateToDetail(bankDetail) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_bank),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "#${bankDetail.bankCode}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(text = bankDetail.bankBranch, fontSize = 11.sp)
                Text(text = bankDetail.bankType, fontSize = 11.sp)
                Text(text = "${bankDetail.district}/${bankDetail.city}", fontSize = 11.sp)

            }

            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 4.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = ""
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BankItemPreview() {

    val item = BankDetail(
        246, "ANKARA", "KAZAN", "KAZAN ŞUBESİ/ANKARA*", "Normal",
        "S4200299", "", "\"Atatürk Mah. Ankara Cad.No:69/B Kazan / ANKARA 06980\"", "", "", "",
        "", ""
    )
    BankItem(bankDetail = item, navigateToDetail = {})
}
