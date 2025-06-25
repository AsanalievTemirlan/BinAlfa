package com.example.alfa.presenter.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Spa(height: Int = 10, width: Int = 10) =
    Spacer(modifier = Modifier.size(width = width.dp, height = height.dp))

@Composable
fun SpaH(size: Int = 10) = Spacer(modifier = Modifier.width(width = size.dp))

@Composable
fun SpaV(size: Int = 10) = Spacer(modifier = Modifier.height(height = size.dp))