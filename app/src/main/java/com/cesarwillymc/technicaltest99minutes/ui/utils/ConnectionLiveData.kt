package com.cesarwillymc.technicaltest99minutes.ui.utils

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.cesarwillymc.technicaltest99minutes.extension.TIME_DELAY_4000
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Suppress("DEPRECATION")
class ConnectionLiveData @Inject constructor(@ApplicationContext private val context: Context) :
    LiveData<Boolean>() {
    private val handler = Handler(Looper.getMainLooper())
    var delayMs: Long = TIME_DELAY_4000
        private set

    private var oldValue: Boolean? = null

    private val connManager: ConnectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    private lateinit var connCallback: ConnectivityManager.NetworkCallback

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private val requestBuilder: NetworkRequest.Builder = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)

    private val networkStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            notifyStatus()
        }
    }

    override fun onInactive() {
        super.onInactive()
        connManager.unregisterNetworkCallback(connCallback)
    }

    override fun onActive() {
        super.onActive()

        notifyStatus()

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connManager.registerDefaultNetworkCallback(
                marshMallowCallback()
            )
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> marshMallowStatusRequest()
            else -> {
                context.registerReceiver(
                    networkStatusReceiver,
                    IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                )
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun marshMallowStatusRequest() {
        connManager.registerNetworkCallback(requestBuilder.build(), marshMallowCallback())
    }

    private fun marshMallowCallback(): ConnectivityManager.NetworkCallback {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onCapabilitiesChanged(
                    network: Network,
                    netCapabilities: NetworkCapabilities
                ) {
                    netCapabilities.let { capabilities ->
                        when {
                            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                                    && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) -> {
                                postValue(true)
                            }
                        }
                    }
                }

                override fun onLost(network: Network) {
                    postValue(false)
                }
            }
            return connCallback
        } else {
            throw IllegalAccessError("Accessing wrong API version")
        }
    }

    private fun notifyStatus() {
        val activeNetwork: NetworkInfo? = connManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }

    override fun postValue(value: Boolean?) {
        if (value != null && value != oldValue) {
            oldValue = value
            if (value) {
                handler.postDelayed({
                    super.postValue(value)
                }, delayMs)
            } else {
                super.postValue(value)
            }
        }
    }

}