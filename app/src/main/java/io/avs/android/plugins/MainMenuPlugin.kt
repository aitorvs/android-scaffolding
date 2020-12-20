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
package io.avs.android.plugins

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import io.avs.android.MainComponentScope
import io.avs.android.di.scopes.MainScope

interface MainMenuPlugin {
  fun menuItemId(): Int
  fun menuItemName(): String
  fun onMenuItemClicked()
}

@Module
@ContributesTo(MainScope::class)
abstract class MainMenuPluginPointModule {
  // we use multibinds as the list of plugins can be empty
  @Multibinds
  abstract fun provideMainMenuPlugins(): Set<@JvmSuppressWildcards MainMenuPlugin>

  @Module
  @ContributesTo(MainScope::class)
  object MainMenuPluginPointExtModule {
    @Provides
    @MainComponentScope
    fun provideMainMenuPluginPoint(
      plugins: Set<@JvmSuppressWildcards MainMenuPlugin>
    ): PluginPoint<MainMenuPlugin> = MainMenuPluginPoint(plugins)
  }
}

class MainMenuPluginPoint(
  private val plugins: Set<MainMenuPlugin>
) : PluginPoint<MainMenuPlugin> {
  override fun getPlugins(): List<MainMenuPlugin> {
    return plugins.toList()
  }
}
