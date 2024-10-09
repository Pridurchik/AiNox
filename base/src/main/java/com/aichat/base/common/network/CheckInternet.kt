package org.noxai.base.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class CheckInternet {
    companion object {
        fun isConnect(context: Context): Boolean {

            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

            return activeNetwork?.isConnectedOrConnecting == true
        }
    }
}