package com.example.videowidget

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class VideoWidgetService : Service() {
    private var exoPlayer: ExoPlayer? = null

    override fun onCreate() {
        super.onCreate()
        exoPlayer = ExoPlayer.Builder(applicationContext).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            playWhenReady = true
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.getStringExtra("VIDEO_URI")?.let {
            exoPlayer?.setMediaItem(MediaItem.fromUri(it))
            exoPlayer?.prepare()
            exoPlayer?.play()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        exoPlayer?.release()
        exoPlayer = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
