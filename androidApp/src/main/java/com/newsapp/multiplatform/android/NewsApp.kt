package com.newsapp.multiplatform.android

import android.app.Application
import android.content.Context
import android.util.Log
import com.newsapp.multiplatform.android.ui.viewmodels.HomeViewModel
import com.newsapp.multiplatform.android.ui.viewmodels.SavedViewModel
import com.newsapp.multiplatform.di.initKoin
import com.newsapp.multiplatform.util.NapierInit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class NewsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            appModule = module {
                single<Context> { this@NewsApp }
                single {
                    { Log.i("Startup", "Hello from Android/Kotlin!") }
                }
                viewModel { HomeViewModel(get(), get()) }
                viewModel { SavedViewModel(get(), get()) }
            },
            enableNetworkLogging = BuildConfig.DEBUG
        )

        if (BuildConfig.DEBUG) NapierInit().init()
    }
}