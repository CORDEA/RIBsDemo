package jp.cordea.ribsdemo.event.region

import jp.cordea.ribsdemo.di.FragmentScope
import jp.cordea.ribsdemo.event.BaseSink
import javax.inject.Inject

@FragmentScope
class RegionActionCreator @Inject constructor(
    private val sink: BaseSink<RegionAction>
) {
    fun init() = sink.dispatch(RegionAction.Init)
    fun refresh() = sink.dispatch(RegionAction.Refresh)
    fun clickedItem(position: Int) = sink.dispatch(RegionAction.ClickedItem(position))
}
