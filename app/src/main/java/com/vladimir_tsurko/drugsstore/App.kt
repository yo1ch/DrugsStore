package com.vladimir_tsurko.drugsstore

import android.app.Application
import com.vladimir_tsurko.drugsstore.di.DaggerApplicationComponent

class App: Application() {
    val component by lazy{
        DaggerApplicationComponent.factory().create(this)
    }
}