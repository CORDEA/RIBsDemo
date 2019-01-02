package jp.cordea.ribsdemo.ui.app

import dagger.Binds
import dagger.Module
import jp.cordea.ribsdemo.event.BaseSink
import jp.cordea.ribsdemo.event.BaseSource
import jp.cordea.ribsdemo.event.app.AppAction
import jp.cordea.ribsdemo.event.app.AppDispatcher

@Module
interface AppFragmentBindsModule {
    @Binds
    fun bindSink(dispatcher: AppDispatcher): BaseSink<AppAction>

    @Binds
    fun bindSource(dispatcher: AppDispatcher): BaseSource<AppAction>
}
