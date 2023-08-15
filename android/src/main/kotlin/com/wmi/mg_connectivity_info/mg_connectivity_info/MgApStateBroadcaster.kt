package com.wmi.mg_connectivity_info.mg_connectivity_info

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import io.flutter.plugin.common.EventChannel.EventSink

class MgApStateBroadcaster(eventSink: EventSink?) : BroadcastReceiver() {
    private val eventSink = eventSink

    override fun onReceive(context: Context?, p0: Intent?) {
        val wifiManager = context!!.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val method = wifiManager.javaClass.getMethod("isWifiApEnabled")
        method.isAccessible = true
        val isApEnabled = method.invoke(wifiManager)

        eventSink?.success(isApEnabled)
    }
}