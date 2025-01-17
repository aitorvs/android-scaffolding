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
package io.avs.android.feature.menu

import android.content.Context
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import io.avs.android.MainComponentScope
import io.avs.android.di.scopes.MainScope
import io.avs.android.plugins.MainMenuPlugin
import org.jetbrains.anko.toast

private const val ID = 6601

private class MenuItemPlugin(
  private val context: Context
) : MainMenuPlugin {
  override fun menuItemId(): Int {
    return ID
  }

  override fun menuItemName(): String {
    return "menu item"
  }

  override fun onMenuItemClicked() {
    context.toast("Plugin menu item")
  }
}

@Module
@ContributesTo(MainScope::class)
class MenuItemModule {
  @Provides
  @MainComponentScope
  @IntoSet
  fun provideMenuItemPlugin(context: Context): MainMenuPlugin = MenuItemPlugin(context)
}
