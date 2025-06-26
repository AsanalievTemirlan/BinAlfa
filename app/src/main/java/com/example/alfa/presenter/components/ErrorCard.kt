package com.example.alfa.presenter.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfa.R
import com.example.alfa.utills.UiState


fun parseErrorMessage(rawMessage: String): String {
    val code = Regex("""Error:\s*(\d{3})""").find(rawMessage)?.groupValues?.get(1)?.toIntOrNull()

    return when (code) {
        400 -> "Некорректный BIN. Проверьте введённый номер."
        429 -> "Слишком много запросов. Подождите и попробуйте снова."
        404 -> "Данные не найдены. Возможно, такого BIN не существует."
        in 500..599 -> "Сервер временно недоступен. Попробуйте позже."
        else -> "Произошла ошибка: $rawMessage"
    }
}


@Composable
fun ErrorCard(error: String) {
    val message = parseErrorMessage(error)

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF53B38)),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_error),
                contentDescription = "Ошибка",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = message,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}
