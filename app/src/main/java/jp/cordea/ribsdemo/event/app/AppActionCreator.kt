package jp.cordea.ribsdemo.event.app

import jp.cordea.ribsdemo.di.FragmentScope
import jp.cordea.ribsdemo.event.BaseSink
import javax.inject.Inject

@FragmentScope
class AppActionCreator @Inject constructor(
    private val sink: BaseSink<AppAction>
) {
    fun init() = sink.dispatch(AppAction.Init)
    fun refresh() = sink.dispatch(AppAction.Refresh)
}
