package com.aichat.ainox

import android.app.Application
import org.noxai.features.DaggerAppComponent

class App: Application() {
    companion object {
        lateinit var app: App
    }

    init {
        app = this
    }

    val baseComponent by lazy {
        DaggerAppComponent.factory()
    }
}