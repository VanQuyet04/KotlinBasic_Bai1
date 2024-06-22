package com.example.kotlinbasic_bai1.activity_intent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text

class activity1 : ComponentActivity() {
    private val TAG = "Activity1Lifecycle"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")

        setContent {
            Button(onClick = {
                val intent = Intent(this, activity2::class.java)
                intent.putExtra("name", "Đây là dữ liệu từ activity 1 ")
                startActivity(intent)
            }) {
                Text("Go to Activity2")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onstart called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")

    }

}

