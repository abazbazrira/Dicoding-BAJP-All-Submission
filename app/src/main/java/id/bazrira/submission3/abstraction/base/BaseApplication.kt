package id.bazrira.submission3.abstraction.base

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.bazrira.submission3.di.component.DaggerAppComponent

class BaseApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerAppComponent.builder().application(this).build()
}