package jp.cordea.ribsdemo.event.app

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import jp.cordea.ribsdemo.di.FragmentScope
import jp.cordea.ribsdemo.event.BaseDispatcher
import javax.inject.Inject

@FragmentScope
class AppDispatcher @Inject constructor() : BaseDispatcher<AppAction> {
    private val _reader = PublishProcessor.create<AppAction>().toSerialized()
    override val reader: Flowable<AppAction> = _reader

    override fun dispatch(action: AppAction) = _reader.onNext(action)
}
