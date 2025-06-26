package com.example.alfa.presenter.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alfa.presenter.ui.theme.MyBlue

@Composable
fun Spa(height: Int = 10, width: Int = 10) =
    Spacer(modifier = Modifier.size(width = width.dp, height = height.dp))

@Composable
fun SpaH(size: Int = 10) = Spacer(modifier = Modifier.width(width = size.dp))

@Composable
fun SpaV(size: Int = 10) = Spacer(modifier = Modifier.height(height = size.dp))

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(48.dp),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MyBlue,
            contentColor = Color.White
        )
    ) {
        Text(text, fontSize = 20.sp)
    }
}

@Composable
fun CardText(text: String) = Text(text, color = Color.White, fontSize = 16.sp)
