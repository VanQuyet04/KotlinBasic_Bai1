package com.example.kotlinbasic_bai1.service.foreground_service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.kotlinbasic_bai1.R

class MusicForegroundService : Service() {

    companion object {
        private const val PAUSE_ACTION =
            "com.example.kotlinbasic_bai1.service.foreground_service.PAUSE"
        private const val PLAY_ACTION =
            "com.example.kotlinbasic_bai1.service.foreground_service.PLAY"
        private const val REQUEST_CODE_PAUSE = 101
        private const val REQUEST_CODE_PLAY = 102
        private const val NOTIFICATION_ID = 1
    }

    private lateinit var mediaPlayer: MediaPlayer

    private val CHANNEL_ID = "MusicServiceChannel"

    private var isPlaying: Boolean = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        mediaPlayer = MediaPlayer.create(this, R.raw.dunglamtraitimadau)
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handleIntent(intent)
        return START_STICKY
    }

    private fun handleIntent(intent: Intent?) {
        when (intent?.action) {
            PAUSE_ACTION -> {
                if (isPlaying) {
                    mediaPlayer.pause()
                    isPlaying = false
                    updateNotification()
                }
            }

            PLAY_ACTION -> {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlaying = true
                    updateNotification()
                }
            }

            else -> {
                if (!isPlaying) {
                    mediaPlayer.start()
                    isPlaying = true
                    updateNotification()
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private fun updateNotification() {
        val notificationIntent = Intent(this, ForegroundActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val pauseIntent = Intent(this, MusicForegroundService::class.java).apply {
            action = PAUSE_ACTION
        }
        val pausePendingIntent = PendingIntent.getService(
            this, REQUEST_CODE_PAUSE, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val playIntent = Intent(this, MusicForegroundService::class.java).apply {
            action = PLAY_ACTION
        }
        val playPendingIntent = PendingIntent.getService(
            this, REQUEST_CODE_PLAY, playIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val pauseAction = NotificationCompat.Action.Builder(
            R.drawable.ic_pause,
            "Pause",
            pausePendingIntent
        ).build()

        val playAction = NotificationCompat.Action.Builder(
            R.drawable.ic_play,
            "Play",
            playPendingIntent
        ).build()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Service")
            .setContentText("Playing music in the foreground")
            .setSmallIcon(R.drawable.ic_music)
            .setContentIntent(pendingIntent)
            .addAction(pauseAction)
            .addAction(playAction)
            .build()

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Music Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

}
