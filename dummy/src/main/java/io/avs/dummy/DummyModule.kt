package io.avs.dummy

import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Singleton

//@ContributesTo(
//  scope = AppScope::class
//)
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