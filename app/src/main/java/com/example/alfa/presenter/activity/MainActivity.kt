package com.example.alfa.presenter.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.alfa.presenter.navigation.AppNavHost
import com.example.alfa.presenter.ui.theme.AlfaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlfaTheme {
                AppNavHost()
            }
        }
    }
}
//fun openUrl(url: String) {
//    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//    startActivity(intent)
//}
//
//fun openPhone(phone: String) {
//    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
//    startActivity(intent)
//}
//
//fun openMaps(countryCode: String) {
//    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$countryCode"))
//    startActivity(intent)
//}