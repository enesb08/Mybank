package com.enesbaskaya.mybank.ui.screen.setting


import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.enesbaskaya.mybank.R
import kotlinx.coroutines.launch


@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingScreenViewModel = hiltViewModel()
) {
    var clickedBottomSheet by remember { mutableStateOf(false) }
    val context = LocalContext.current

    SettingContent(onBackClick = {
        navController.popBackStack()
    },
        onChangeLanguageClick = {
            clickedBottomSheet = true
        }

    )

    if (clickedBottomSheet) {
        BottomSheet(onDismiss = {
            clickedBottomSheet = false
        }, changeLanguage = {
            viewModel.changeLanguage(it,
                restartApp = {
                    val i: Intent? = context.getPackageManager()
                        .getLaunchIntentForPackage(context.getPackageName())
                    i?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    i?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(i)
                    System.exit(0)

                })

        })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(changeLanguage: (Languages) -> Unit, onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val hideModalBottomSheet: () -> Unit =
        {
            coroutineScope.launch {
                modalBottomSheetState.hide()
                onDismiss()
            }
        }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        BoxWithConstraints(
            Modifier
                .navigationBarsPadding()
                .padding(bottom = 60.dp)
        ) {
            LazyColumn {
                items(Languages.entries) {
                    Row(
                        modifier = Modifier
                            .clickable {
                                changeLanguage.invoke(it)
                                hideModalBottomSheet()
                            }
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 20.dp)
                    ) {
                        Text(
                            text = it.flag,
                            modifier = Modifier.padding(end = 20.dp)
                        )
                        val langText = if (it.key == "tr") {
                            stringResource(id = R.string.tv_tr)
                        } else {
                            stringResource(id = R.string.tv_en)

                        }
                        Text(text = langText)
                    }
                }
            }
        }

    }
}

enum class Languages(val key: String, val flag: String) {
    TURKISH("tr", "\uD83C\uDDF9\uD83C\uDDF7"),
    ENGLISH("en", "\uD83C\uDDEC\uD83C\uDDE7")

}


