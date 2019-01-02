package jp.cordea.ribsdemo.event.regiondetail

import jp.cordea.ribsdemo.di.ActivityScope
import jp.cordea.ribsdemo.event.BaseSink
import javax.inject.Inject

@ActivityScope
class RegionDetailActionCreator @Inject constructor(
    private val sink: BaseSink<RegionDetailAction>
) {
    fun init() = sink.dispatch(RegionDetailAction.Init)
}
