package com.cesarwillymc.technicaltest99minutes.data.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class ConnectionUtilsImpl @Inject constructor(@ApplicationContext private val applicationContext: Context) :
    ConnectionUtils {

    @Suppress("DEPRECATION")
    override fun isNetworkAvailable(): Boolean {
        return try {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> connectivityManager.getConnectionApiM()
                else -> {
                    val nwInfo = connectivityManager.activeNetworkInfo
                    nwInfo?.isConnected ?: false
                }
            }
        } catch (e: Exception) {
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun ConnectivityManager.getConnectionApiM(): Boolean {
        val nw = activeNetwork
        val actNw = getNetworkCapabilities(nw)
        return when {
            actNw?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> true
            actNw?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> true
            actNw?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> true
            else -> false
        }
    }
}
