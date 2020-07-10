package com.orange.internship.usgsearthquakesapi_usingretrofit

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single { UsgsApi.create(androidContext()) }
}

val presenterModule = module {
    factory { (view: MainContract.View) ->
        MainPresenter(
            view
        )
    }
}