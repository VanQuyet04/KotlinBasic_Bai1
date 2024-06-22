package com.example.kotlinbasic_bai1.service.bound_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService : Service() {

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): BoundService = this@BoundService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    // Phương thức tính toán nhận 2 số và trả về kết quả
    fun addNumbers(num1: Int, num2: Int): Int {
        return num1 + num2
    }
}
