package jp.cordea.ribsdemo.event.region

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import jp.cordea.ribsdemo.di.FragmentScope
import jp.cordea.ribsdemo.event.BaseDispatcher
import javax.inject.Inject

@FragmentScope
class RegionDispatcher @Inject constructor() : BaseDispatcher<RegionAction> {
    private val _reader = PublishProcessor.create<RegionAction>().toSerialized()
    override val reader: Flowable<RegionAction> = _reader

    override fun dispatch(action: RegionAction) = _reader.onNext(action)
}
