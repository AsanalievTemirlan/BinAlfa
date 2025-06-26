package com.example.alfa.presenter.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.alfa.R
import com.example.alfa.presenter.screnns.CardHistoryScreen
import com.example.alfa.presenter.screnns.MainScreen
import com.example.alfa.presenter.ui.theme.MyBlack
import com.example.alfa.presenter.ui.theme.MyBlue
import com.example.alfa.presenter.ui.theme.MyBlue2
import com.example.alfa.presenter.ui.theme.MyGray

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val startDestination = Routes.MAIN

    val bottomNavScreens = listOf(
        Routes.MAIN,
        Routes.CARD
    )
    val items = listOf(
        ScreensBottom.Home,
        ScreensBottom.History
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavScreens) {
                NavigationBar(
                    containerColor = MyBlue,
                    contentColor = Color.White,
                    tonalElevation = 0.dp,
                    modifier = Modifier
                        .background(MyBlack)
                        .clip(
                            RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 12.dp,
                                bottomEnd = 12.dp,
                                bottomStart = 12.dp
                            )
                        )

                ) {
                    items.forEach { screen ->
                        NavigationBarItem(
                            selected = currentRoute == screen.route,
                            onClick = {
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.iconRes),
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = screen.title,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (currentRoute == screen.route) Color.White else MyGray
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                unselectedIconColor = MyGray,
                                selectedTextColor = Color.White,
                                unselectedTextColor = MyGray,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    )
    { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.MAIN) { MainScreen(navController = navController) }
            composable(Routes.CARD) { CardHistoryScreen(navController = navController) }
        }
    }
}

object Routes {
    const val MAIN = "main"
    const val CARD = "card"
}

sealed class ScreensBottom(val route: String, val title: String, val iconRes: Int) {
    data object Home : ScreensBottom(Routes.MAIN, "Home", R.drawable.ic_circle)
    data object History : ScreensBottom(Routes.CARD, "History", R.drawable.ic_book)
}