package io.avs.android.di.scopes

object ComponentHolder {
  val components = mutableMapOf<Any, Any>()

  // this could also be `components[T::class.java] as T` but I rather
  // iterate over the real values and filter by instance
  inline fun <reified T> component(): T = components.values
    .filterIsInstance<T>()
    .single()
}
