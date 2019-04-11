package com.skydoves.allinone.di

import com.skydoves.allinone.view.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  internal abstract fun contributeMainActivity(): MainActivity
}