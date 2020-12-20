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

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.avs.android.di.AppComponent
import io.avs.android.di.scopes.ComponentHolder
import io.avs.android.menu.MainMenuBuilder
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
  @Inject lateinit var mainMenuBuilder: MainMenuBuilder

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val provider = ComponentHolder.component<AppComponent>() as MainComponentProvider
    val component = provider.mainComponentBuilder()
      .bindActivity(this)
      .build().apply {
        inject(this@MainActivity)
      }
    ComponentHolder.components[MainComponent::class.java] = component

    setContentView(R.layout.activity_main)
  }

  override fun onDestroy() {
    super.onDestroy()
    ComponentHolder.components.remove(MainComponent::class.java)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    menu?.let { mainMenuBuilder.buildMenu(menu) }

    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.permanent -> toast("Permanent menu item")
      else -> mainMenuBuilder.onMenuItemClicked(item.itemId)
    }
    return super.onOptionsItemSelected(item)
  }
}
