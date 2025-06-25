package com.example.alfa.presenter.screnns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alfa.presenter.components.openMaps
import com.example.alfa.presenter.components.openPhone
import com.example.alfa.presenter.components.openUrl
import com.example.alfa.presenter.viewmodel.BinViewModel
import com.example.alfa.utills.LogUtil
import com.example.alfa.utills.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    binViewModel: BinViewModel = koinViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    var binNumber by remember { mutableStateOf("") }
    val uiState by binViewModel.binInfo.collectAsState()

    val binInfo = (uiState as? UiState.Success)?.data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        TextField(
            value = binNumber,
            onValueChange = { binNumber = it },
            label = { Text("Введите BIN номер") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions.Default
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                binViewModel.fetchBinInfo(binNumber)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Получить информацию")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is UiState.Error -> {
                Text(
                    text = "Ошибка: ${(uiState as UiState.Error).message}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is UiState.Success -> {
                binInfo?.let { info ->
                    LogUtil.i("Info $info")
                    Text("Страна: ${info.countryModel.name}", fontWeight = FontWeight.Bold)
                    Text("Тип карты: ${info.type}")
                    Text("Бренд карты: ${info.brand}")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Данные банка:")
                    Text("Название банка: ${info.brand}")
                    Text("Город: ${info.bankModel.city}")
                    Text("Телефон: ${info.bankModel.phone}")
                    Text("Сайт: ${info.bankModel.url}")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { openUrl(context, info.bankModel.url) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Перейти на сайт банка")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { openPhone(context, info.bankModel.phone) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Позвонить в банк")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { openMaps(context, info.countryModel.alpha2) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Открыть карту страны")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            navController.navigate("history_screen")
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Перейти в историю запросов")
                    }
                }
            }
        }
    }
}