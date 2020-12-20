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
package io.avs.android.menu

import android.view.Menu
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import io.avs.android.MainComponentScope
import io.avs.android.di.scopes.MainScope
import io.avs.android.plugins.MainMenuPlugin
import io.avs.android.plugins.PluginPoint

interface MainMenuBuilder {
  fun buildMenu(menu: Menu)
  fun onMenuItemClicked(id: Int)
}

private class RealMainMenuBuilder(
  private val menus: PluginPoint<MainMenuPlugin>
) : MainMenuBuilder {
  override fun buildMenu(menu: Menu) {
    menus.getPlugins().forEach { item ->
      menu.add(Menu.NONE, item.menuItemId(), Menu.NONE, item.menuItemName())
    }
  }

  override fun onMenuItemClicked(id: Int) {
    menus.getPlugins().firstOrNull { it.menuItemId() == id }?.onMenuItemClicked()
  }
}

@Module
@ContributesTo(MainScope::class)
class BrowserMenuBuilderModule {
  @Provides
  @MainComponentScope
  fun provideBrowserMenuBuilder(
    mainMenuPluginPoint: PluginPoint<MainMenuPlugin>
  ): MainMenuBuilder {
    return RealMainMenuBuilder(mainMenuPluginPoint)
  }
}
