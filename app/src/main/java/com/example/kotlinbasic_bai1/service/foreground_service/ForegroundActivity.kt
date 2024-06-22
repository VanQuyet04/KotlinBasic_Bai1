package com.example.kotlinbasic_bai1.service.foreground_service

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kotlinbasic_bai1.service.background_service.MusicBackgroundService

class ForegroundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column {
                Text(text = "FOREGROUND SERVICE ACTIVITY", modifier = Modifier.padding(15.dp))

                Button(onClick = {
                    startService(
                        Intent(
                            this@ForegroundActivity,
                            MusicForegroundService::class.java
                        )
                    )
                }) {
                    Text("Start Music Service")
                }

                Button(onClick = {
                    stopService(Intent(this@ForegroundActivity, MusicForegroundService::class.java))
                }) {
                    Text("Stop Music Service")
                }
            }
        }
    }
}
