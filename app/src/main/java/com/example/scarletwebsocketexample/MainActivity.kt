package com.example.scarletwebsocketexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tinder.scarlet.WebSocket

class MainActivity : AppCompatActivity() {

    private val scarlet = ScarletProvide.scarlet

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scarlet.observeConnection().subscribe {
            when(it) {
                is WebSocket.Event.OnConnectionOpened<*> -> {
                    Log.d("MainActivityScarlet", "observeConnection: ConnectionOpened")
                    scarlet.sendMessage("Hello Scarlet!")
                }
                is WebSocket.Event.OnConnectionClosed -> {
                    Log.e("MainActivityScarlet", "observeConnection: OnConnectionClosed")
                }
                is WebSocket.Event.OnConnectionClosing -> {
                    Log.e("MainActivityScarlet", "observeConnection: OnConnectionClosing")
                }
                is WebSocket.Event.OnConnectionFailed -> {
                    Log.d("MainActivityScarlet", "observeConnection: ${it.throwable.message}")
                }
                is WebSocket.Event.OnMessageReceived -> {
                    Log.d("MainActivityScarlet", "observeConnection: ${it.message} Received!")
                }
            }
        }
    }
}