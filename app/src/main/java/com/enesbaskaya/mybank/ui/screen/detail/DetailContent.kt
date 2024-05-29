package com.enesbaskaya.mybank.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesbaskaya.mybank.R
import com.enesbaskaya.mybank.data.model.BankDetail
import com.enesbaskaya.mybank.ui.widget.AppBarWithBackButton
import com.enesbaskaya.mybank.ui.widget.MyIcon

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onGoAddressClick: () -> Unit,
    bankDetail: BankDetail
) {

    AppBarWithBackButton(
        title = stringResource(id = R.string.title_bank_detail),
        onBackClick = onBackClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(modifier),
        ) {

            Card(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 6.dp)
                    .fillMaxWidth(),
                border = BorderStroke(0.5.dp, colorResource(id = R.color.item_blue)),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent,
                ),
                shape = RoundedCornerShape(corner = CornerSize(12.dp)),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    BankHeaderInfoContent(bankDetail)

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .fillMaxWidth()
                    ) {



                        BankBaseInfoContent(bankDetail)
                        Spacer(modifier = Modifier.size(10.dp))

                        AddressDetailContent(bankDetail)

                        Spacer(modifier = Modifier.size(16.dp))
                        OutlinedButton(
                            modifier = modifier
                                .align(Alignment.CenterHorizontally)
                                .height(40.dp),
                            onClick = {
                                onGoAddressClick.invoke()

                            },
                            border = BorderStroke(1.dp, Color.Red),
                            shape = RoundedCornerShape(30),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                        ) {


                            Text(text = stringResource(id = R.string.btn_go_address))

                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_go_address),
                                contentDescription = ""
                            )

                        }

                        Spacer(modifier = Modifier.size(16.dp))


                    }
                }
            }
        }
    }
}

@Composable
private fun BankHeaderInfoContent(bankDetail: BankDetail) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_bank),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 8.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )

        val isOnlineColor = if (bankDetail.onOffLine.equals("On Line")) {
            Color.Green
        } else {
            Color.Red
        }

        val isOnlineText = if (bankDetail.onOffLine.equals("On Line")) {
            stringResource(id = R.string.tv_on)
        } else {
            stringResource(id = R.string.tv_off)

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .border(1.dp, Color.White, CircleShape)
                    .padding(1.dp)
                    .clip(CircleShape)
                    .background(isOnlineColor)
            )
            Spacer(modifier = Modifier.size(2.dp))

            Text(
                text = isOnlineText,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            )

        }


    }
}

@Composable
private fun BankBaseInfoContent(bankDetail: BankDetail) {
    val bankCode =
        stringResource(R.string.tv_bank_code) + "#${bankDetail.bankCode}"
    val bankBranch =
        stringResource(R.string.tv_bank_branch) + " ${bankDetail.bankBranch}"
    val bankType =
        stringResource(R.string.tv_bank_type) + " ${bankDetail.bankType}"

    Row (verticalAlignment = Alignment.CenterVertically){
        MyIcon(
            resIcon = R.drawable.ic_info,
            resBgColor = R.color.circle_bg_1
        )
        Text(
            text = stringResource(id = R.string.title_bank_info),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        )
    }

    Divider(

        color = colorResource(id = R.color.item_blue),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 8.dp)
            .height(0.5.dp)
    )

    Text(text = bankCode, fontSize = 12.sp)
    Text(text = bankBranch, fontSize = 12.sp)
    Text(text = bankType, fontSize = 12.sp)
}

@Composable
private fun AddressDetailContent(
    bankDetail: BankDetail,
) {

    val cityAndDistrict =
        stringResource(R.string.tv_city_and_district) + "${bankDetail.district}/${bankDetail.city}"
    stringResource(R.string.tv_on_off_site) + "${bankDetail.district}/${bankDetail.city}"
    val isOnSite = if (bankDetail.onOffSite.equals("On Site")) {
        stringResource(R.string.tv_on_off_site) + stringResource(id = R.string.tv_on_site)
    } else {
        stringResource(R.string.tv_on_off_site) + stringResource(id = R.string.tv_off_site)
    }

    Row (verticalAlignment = Alignment.CenterVertically){
        MyIcon(
            resIcon = R.drawable.ic_address,
            resBgColor = R.color.circle_bg_2
        )
        Text(
            text = stringResource(id = R.string.title_address_info),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        )
    }

    Divider(

        color = colorResource(id = R.color.item_blue),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 8.dp)
            .height(0.5.dp)
    )

    Text(
        text = stringResource(R.string.tv_address_name) + " ${bankDetail.addressName}",
        fontSize = 12.sp
    )
    Text(
        text = stringResource(R.string.tv_address_detail) + " ${bankDetail.addressDetail}",
        fontSize = 12.sp
    )
    Text(
        text = isOnSite,
        fontSize = 12.sp
    )
    Text(
        text = stringResource(R.string.tv_postal_code) + " ${bankDetail.postalCode}",
        fontSize = 12.sp
    )

    Text(
        text = stringResource(R.string.tv_region_coordinatorship) + " ${bankDetail.regionCoordinatorship}",
        fontSize = 12.sp
    )
    Text(text = cityAndDistrict, fontSize = 12.sp)
    Spacer(modifier = Modifier.size(10.dp))

    Row (verticalAlignment = Alignment.CenterVertically){
        MyIcon(
            resIcon = R.drawable.ic_atm,
            resBgColor = R.color.circle_bg_3
        )
        Text(
            text = stringResource(id = R.string.title_nearest_ATM),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        )
    }

    Divider(

        color = colorResource(id = R.color.item_blue),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 8.dp)
            .height(0.5.dp)
    )

    Text(text = " ${bankDetail.nearestATM}", fontSize = 12.sp)
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val item = BankDetail(
        246, "ANKARA", "KAZAN", "KAZAN ŞUBESİ/ANKARA*", "Normal",
        "S4200299", "", "\"Atatürk Mah. Ankara Cad.No:69/B Kazan / ANKARA 06980\"", "", "", "",
        "", ""
    )
    DetailContent(bankDetail = item, onBackClick = {}, onGoAddressClick = {})
}