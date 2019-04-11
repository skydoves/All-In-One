package com.skydoves.allinone

import android.app.Application
import timber.log.Timber

@Suppress("unused")
class AllInOneApplication: Application() {

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}