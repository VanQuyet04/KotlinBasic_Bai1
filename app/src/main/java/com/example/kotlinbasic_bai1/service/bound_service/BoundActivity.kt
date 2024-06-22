package com.example.kotlinbasic_bai1.service.bound_service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.kotlinbasic_bai1.ui.theme.KotlinBasic_Bai1Theme

class BoundActivity : ComponentActivity() {

    private var boundService: BoundService? = null
    private var isBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as BoundService.LocalBinder
            boundService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinBasic_Bai1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculationScreen()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //Khởi động boundservice, bind service, kết nối vơí activity thông qua serviceConnection
        Intent(this, BoundService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        // unbound
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    @Composable
    fun CalculationScreen() {
        var num1 by remember { mutableStateOf(TextFieldValue("")) }
        var num2 by remember { mutableStateOf(TextFieldValue("")) }
        var result by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = num1,
                onValueChange = { num1 = it },
                label = { Text("Enter first number") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = num2,
                onValueChange = { num2 = it },
                label = { Text("Enter second number") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (isBound) {
                    val number1 = num1.text.toIntOrNull() ?: 0
                    val number2 = num2.text.toIntOrNull() ?: 0
                    result = boundService?.addNumbers(number1, number2) ?: 0
                }
            }) {
                Text("Calculate")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Result: $result")
        }
    }
}
