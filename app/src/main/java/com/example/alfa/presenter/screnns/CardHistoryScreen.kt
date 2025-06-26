package com.example.alfa.presenter.screnns

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alfa.domain.model.BinModel
import com.example.alfa.presenter.viewmodel.CardViewModel
import com.example.alfa.utills.UiState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.alfa.presenter.components.CardText
import com.example.alfa.presenter.components.Spa
import com.example.alfa.presenter.ui.theme.MyBlack
import com.example.alfa.presenter.ui.theme.MyBlue
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardHistoryScreen(
    navController: NavController,
    cardViewModel: CardViewModel = koinViewModel()
) {
    val uiState by cardViewModel.card.collectAsState()
    var selectedBin by remember { mutableStateOf<BinModel?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MyBlack)
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        Text(
            text = "История запросов",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (uiState) {
            is UiState.Loading -> {
                androidx.compose.material3.CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Error -> {
                Text(
                    text = (uiState as UiState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is UiState.Success -> {
                val cards = (uiState as UiState.Success<List<BinModel>>).data
                if (cards.isEmpty()) {
                    Text(
                        text = "История пуста",
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    androidx.compose.foundation.lazy.LazyColumn {
                        items(cards) { binModel ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable{selectedBin = binModel}
                                    .padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "BIN: ${binModel.bin}",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        text = "Страна: ${binModel.countryModel.name}",
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = "Банк: ${binModel.bankModel.name}",
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    selectedBin?.let { info ->
        AlertDialog(
            onDismissRequest = { selectedBin = null },
            confirmButton = {},
            containerColor = MyBlue,
            title = {
                Text(
                    text = "Информация о BIN",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    CardText("Страна: ${info.countryModel.name}")
                    CardText("Тип карты: ${info.type}")
                    CardText("Бренд: ${info.brand}")

                    Spa(8)

                    Text("Данные банка:", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    CardText("Название: ${info.bankModel.name}")
                    CardText("Город: ${info.bankModel.city}")
                    CardText("Телефон: ${info.bankModel.phone}")
                    CardText("Сайт: ${info.bankModel.url}")
                }
            }
        )
    }
}