package com.manuelsagra.todo.ui

import android.app.Application
import com.facebook.stetho.Stetho
import com.manuelsagra.todo.di.koin.appModule
import org.koin.android.ext.android.startKoin

class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules = listOf(appModule))

        Stetho.initializeWithDefaults(this)
    }

}