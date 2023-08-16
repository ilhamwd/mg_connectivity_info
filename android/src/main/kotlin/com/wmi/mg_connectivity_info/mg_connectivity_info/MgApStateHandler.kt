package com.wmi.mg_connectivity_info.mg_connectivity_info

import android.content.Context
import android.content.IntentFilter
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel

class MgApStateHandler(context: Context, messenger: BinaryMessenger) {
    private val eventChannel = EventChannel(messenger, "mg/hotspot_status_changed")

    init {
        eventChannel.setStreamHandler(
            object : EventChannel.StreamHandler {
                override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                    context.registerReceiver(
                        MgApStateBroadcaster(events),
                        IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED")
                    )
                }

                override fun onCancel(arguments: Any?) {
                }

            }
        )
    }
}