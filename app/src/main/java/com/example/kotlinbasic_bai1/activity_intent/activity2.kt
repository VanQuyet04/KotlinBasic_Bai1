package com.example.kotlinbasic_bai1.activity_intent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text

class activity2 : ComponentActivity() {
    private val TAG = "Activity2Lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")

        setContent {
            var name = intent.getStringExtra("name")
            Text(text = "Welcome $name")
            Button(onClick = {
                val intent = Intent(this, activity1::class.java)
                startActivity(intent)
            }) {
                Text("Go to Activity1")
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