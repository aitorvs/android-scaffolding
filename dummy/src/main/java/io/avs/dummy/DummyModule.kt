package io.avs.dummy

import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.compat.MergeModules
import dagger.Module
import dagger.Provides
import io.avs.android.di.scopes.AppScope
import timber.log.Timber
import javax.inject.Singleton

@ContributesTo(
  scope = AppScope::class
)
@Module
//@MergeModules(
//  scope = AppScope::class
//)
class DummyModule {
  @Provides
  @Singleton
  fun provideDummy() = Dummy()
}

class Dummy {
  fun dummy() {
    Timber.d("")
  }
}