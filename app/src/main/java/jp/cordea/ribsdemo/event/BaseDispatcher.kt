package jp.cordea.ribsdemo.event

import io.reactivex.Flowable

interface BaseDispatcher<T> : BaseSource<T>, BaseSink<T>

interface BaseSource<T> {
    val reader: Flowable<T>
}

interface BaseSink<T> {
    fun dispatch(action: T)
}
