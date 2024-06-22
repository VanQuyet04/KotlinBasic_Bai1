package com.example.kotlinbasic_bai1.broadcast_receive

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinbasic_bai1.ui.theme.KotlinBasic_Bai1Theme

class NetworkActivity : ComponentActivity() {

    private lateinit var networkReceiver: NetworkReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkReceiver = NetworkReceiver(this)
        setContent {
            KotlinBasic_Bai1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NetworkStatusScreen()
                }
            }
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onStart() {
        super.onStart()
        registerReceiver(networkReceiver, networkReceiver.getIntentFilter())
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkReceiver)
    }

}

@Composable
fun NetworkStatusScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Network Status", style = MaterialTheme.typography.labelMedium)
    }
}
