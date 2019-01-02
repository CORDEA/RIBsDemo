package jp.cordea.ribsdemo.ui.region

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import jp.cordea.ribsdemo.event.BaseSink
import jp.cordea.ribsdemo.event.BaseSource
import jp.cordea.ribsdemo.event.region.RegionAction
import jp.cordea.ribsdemo.event.region.RegionDispatcher

@Module
interface RegionFragmentBindsModule {
    @Binds
    fun bindFragment(fragment: RegionFragment): Fragment

    @Binds
    fun bindSink(dispatcher: RegionDispatcher): BaseSink<RegionAction>

    @Binds
    fun bindSource(dispatcher: RegionDispatcher): BaseSource<RegionAction>
}
