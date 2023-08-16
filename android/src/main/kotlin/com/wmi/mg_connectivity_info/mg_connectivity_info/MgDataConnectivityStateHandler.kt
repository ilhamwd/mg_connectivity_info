package com.wmi.mg_connectivity_info.mg_connectivity_info

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink

class MgDataConnectivityStateHandler(context: Context, messenger: BinaryMessenger) {
    private var manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    init {
        val eventChannel = EventChannel(messenger, "mg/data_connection_state")
        eventChannel.setStreamHandler(
            object : EventChannel.StreamHandler {
                override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                    if (ActivityCompat.checkSelfPermission(
                            context,
                            Manifest.permission.READ_PHONE_STATE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        if (manager.dataState == 0) {
                            events?.success("0")
                        } else {
                            events?.success(manager.dataNetworkType)
                        }
                    }

                    if (Build.VERSION.SDK_INT >= 31) {
                        manager.registerTelephonyCallback(
                            context.mainExecutor,
                            object : TelephonyCallback(),
                                TelephonyCallback.DataConnectionStateListener {
                                override fun onDataConnectionStateChanged(
                                    state: Int,
                                    networkType: Int
                                ) {
                                    postMessage(state, networkType, events)
                                }
                            })
                    } else {
                        manager.listen(object : PhoneStateListener() {
                            override fun onDataConnectionStateChanged(
                                state: Int,
                                networkType: Int
                            ) {
                                super.onDataConnectionStateChanged(state)

                                postMessage(state, networkType, events)
                            }
                        }, PhoneStateListener.LISTEN_DATA_CONNECTION_STATE)
                    }
                }

                override fun onCancel(arguments: Any?) {
                    TODO("Not yet implemented")
                }

            }
        )
    }

    private fun postMessage(state: Int, networkType: Int, events: EventSink?): Unit? {
        if (state == TelephonyManager.DATA_DISCONNECTED) {
            return events?.success("0")
        }

        return events?.success(networkType.toString())
    }
}
