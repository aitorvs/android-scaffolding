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

import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import io.avs.android.di.scopes.AppScope
import io.avs.android.di.scopes.MainScope

@MainComponentScope
@MergeSubcomponent(
  scope = MainScope::class
)
interface MainComponent {
  fun inject(activity: MainActivity)

  @Subcomponent.Builder
  interface Builder {
    @BindsInstance
    fun bindActivity(activity: MainActivity): Builder

    fun build(): MainComponent
  }
}

@ContributesTo(AppScope::class)
interface MainComponentProvider {
  fun mainComponentBuilder(): MainComponent.Builder
}

@Module
@ContributesTo(MainScope::class)
class MainModule
