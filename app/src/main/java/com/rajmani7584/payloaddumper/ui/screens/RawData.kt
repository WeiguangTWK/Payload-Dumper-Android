package com.rajmani7584.payloaddumper.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rajmani7584.payloaddumper.R
import com.rajmani7584.payloaddumper.models.DataViewModel
import com.sebastianneubauer.jsontree.JsonTree

@Composable
fun RawData(dataModel: DataViewModel, navController: NavHostController) {
    val raw by dataModel.payloadRaw

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.padding(12.dp)) {
            Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Go Back",
                Modifier.clickable { navController.popBackStack() })
            Spacer(Modifier.height(8.dp))
            Text(
                stringResource(R.string.extract_screen_raw_data),
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily(Font(R.font.doto)),
                modifier = Modifier.padding(start = 8.dp)
            )
            var msg by remember { mutableStateOf<String?>(null) }
            msg?.let { Text(it, color = Color.Red) }
            raw?.let {
                JsonTree(it, onLoading = { Text(stringResource(R.string.raw_data_loading)) }, onError = { err ->
                    msg =
                        err.message.toString()
                }, modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}