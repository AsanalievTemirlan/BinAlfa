package com.example.alfa.presenter.screnns

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alfa.presenter.components.ActionButton
import com.example.alfa.presenter.components.ErrorCard
import com.example.alfa.presenter.components.Spa
import com.example.alfa.presenter.components.openMaps
import com.example.alfa.presenter.components.openPhone
import com.example.alfa.presenter.components.openUrl
import com.example.alfa.presenter.ui.theme.MyBlack
import com.example.alfa.presenter.ui.theme.MyBlue
import com.example.alfa.presenter.viewmodel.BinViewModel
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
    var dialogMessage by remember { mutableStateOf<String?>(null) }

    val binInfo = (uiState as? UiState.Success)?.data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyBlack)
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            "Введите BIN номер",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Spa(30)
        TextField(
            value = binNumber,
            textStyle = TextStyle(
                fontSize = 24.sp,
                color = Color.White
            ),
            placeholder = {
                Text(
                    text = "Например: 45717360",
                    fontSize = 24.sp,
                    color = Color.Gray
                )
            },
            onValueChange = {
                if (it.length <= 8) binNumber = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .background(MyBlack),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    binViewModel.fetchBinInfo(binNumber)
                }
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MyBlack,
                unfocusedContainerColor = MyBlack,
                focusedIndicatorColor = MyBlue,
                unfocusedIndicatorColor = MyBlue,
                disabledContainerColor = MyBlack,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.Gray
            )
        )


        Spa(16)

        when (uiState) {
            is UiState.Loading -> {
                // CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }

            is UiState.Error -> {
                ErrorCard(error = (uiState as UiState.Error).message)
            }

            is UiState.Success -> {
                binInfo?.let { info ->
                    Log.e("ololo", "MainScreen: $info", )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MyBlue
                        ),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Bin: $binNumber",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Страна: ${info.countryModel.name}",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Тип карты: ${info.type}",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Бренд карты: ${info.brand}",
                                color = Color.White,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Данные банка:",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Название банка: ${info.bankModel.name}",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Город: ${info.bankModel.city}",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Телефон: ${info.bankModel.phone}",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Сайт: ${info.bankModel.url}",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }


                    Spa(16)
                    ActionButton(
                        text = "Перейти на сайт банка",
                        onClick = {
                            if (info.bankModel.url == "Unknown URL"  || info.bankModel.url == "Сайт не указан") {
                                dialogMessage = "К сожалению, сайт банка не указан"
                            } else {
                                openUrl(context, info.bankModel.url)
                            }
                        }
                    )

                    Spa(10)

                    ActionButton(
                        text = "Позвонить в банк",
                        onClick = {
                            if (info.bankModel.phone == "Unknown Phone" || info.bankModel.phone == "Телефон не указан") {
                                dialogMessage = "К сожалению, телефон банка не указан"
                            } else {
                                openPhone(context, info.bankModel.phone)
                            }
                        }
                    )

                    Spa(10)

                    ActionButton(
                        text = "Открыть карту страны",
                        onClick = {
                            if (info.countryModel.alpha2 == "Код страны не указан") {
                                dialogMessage = "К сожалению, код страны не указан"
                            } else {
                                openMaps(context, info.countryModel.latitude, info.countryModel.longitude)
                            }
                        }
                    )
                }
            }
        }
    }
    dialogMessage?.let { message ->
        AlertDialog(
            onDismissRequest = { dialogMessage = null },
            confirmButton = {
                Text(
                    "ОК",
                    color = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { dialogMessage = null }
                )
            },
            title = {
                Text("Ошибка", color = Color.White)
            },
            text = {
                Text(message, color = Color.White, fontSize = 20.sp)
            },
            containerColor = Color(0xFFF53B38))
    }

}