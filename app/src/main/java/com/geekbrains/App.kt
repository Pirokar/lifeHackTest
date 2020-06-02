package com.geekbrains

import android.app.Application
import com.geekbrains.lifehacktest.di.AppComponent
import com.geekbrains.lifehacktest.di.DaggerAppComponent
import com.geekbrains.lifehacktest.di.modules.AppModule

class App: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appInstance: App
    }
}