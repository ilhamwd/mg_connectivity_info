package com.wmi.mg_connectivity_info.mg_connectivity_info

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel

class MgConnectivityInfoPlugin() : FlutterPlugin, ActivityAware {
    private lateinit var activity: Activity
    private lateinit var channel: MethodChannel
    private lateinit var context: Context
    private lateinit var binaryMessenger: BinaryMessenger
    private val requiredPermissions = arrayOf(Manifest.permission.READ_PHONE_STATE);

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "mg_connectivity_info")
        context = flutterPluginBinding.applicationContext
        binaryMessenger = flutterPluginBinding.binaryMessenger
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity

        for (permission in requiredPermissions) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(activity, arrayOf(permission), 7890)
            }
        }

        MgDataConnectivityStateHandler(context, binaryMessenger)
        MgApStateHandler(context, binaryMessenger)
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }
}
