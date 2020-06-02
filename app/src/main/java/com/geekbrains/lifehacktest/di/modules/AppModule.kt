package com.geekbrains.lifehacktest.di.modules

import com.geekbrains.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {
    @Provides
    fun app(): App {
        return app
    }
}