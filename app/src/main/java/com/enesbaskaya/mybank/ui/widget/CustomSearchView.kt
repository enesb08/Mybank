package com.enesbaskaya.mybank.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesbaskaya.mybank.R

@Composable
fun CustomSearchView(
    search: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {

    Box(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)

    ) {


        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = search,
            singleLine = true,
            textStyle = TextStyle.Default.copy(fontSize = 12.sp),
            onValueChange = onValueChange,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.primary

            ),
            trailingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.hint_search_bank),
                    style = TextStyle.Default.copy(fontSize = 12.sp),
                )
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun CustomSearchViewPreview() {
    CustomSearchView(search = "", onValueChange = {
    })
}