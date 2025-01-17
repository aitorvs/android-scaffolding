/*
 * Copyright (C) 2020. Aitor Viana
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.avs.android

import android.app.Application
import android.content.Context
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.avs.android.di.AppComponent
import io.avs.android.di.DaggerAppComponent
import io.avs.android.di.scopes.AppScope
import io.avs.android.di.scopes.ComponentHolder
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

open class App : HasAndroidInjector, Application() {
  @Inject
  lateinit var androidInjector: DispatchingAndroidInjector<Any>

  override fun onCreate() {
    super.onCreate()

    configureTimber()
    configureDependencyInjection()

    Timber.i("Creating application")
  }

  private fun configureTimber() {
    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }

  private fun configureDependencyInjection() {
    val appComponent = DaggerAppComponent.builder()
      .application(this)
      .build()

    appComponent.inject(this)

    ComponentHolder.components[AppComponent::class.java] = appComponent
  }

  override fun androidInjector(): AndroidInjector<Any> {
    return androidInjector
  }
}

@Module
@ContributesTo(AppScope::class)
abstract class AppModule {
  @Binds
  @Singleton
  abstract fun bindContext(application: Application): Context
}
