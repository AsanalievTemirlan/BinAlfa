package com.example.alfa.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alfa.presenter.navigation.Routes.CARD
import com.example.alfa.presenter.navigation.Routes.MAIN
import com.example.alfa.presenter.screnns.CardHistoryScreen
import com.example.alfa.presenter.screnns.MainScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    val startDestination = MAIN

    NavHost(navController, startDestination) {
        composable(MAIN) { MainScreen(navController = navController) }
        composable(CARD) { CardHistoryScreen(navController = navController) }
    }
}

object Routes {
    const val MAIN = "main"
    const val CARD = "card"
}