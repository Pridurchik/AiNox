package org.noxai.base.base

import android.app.Application

open class BaseApplication  : Application() {

    companion object {
        lateinit var app: BaseApplication
    }

    init {
        app = this
    }

}