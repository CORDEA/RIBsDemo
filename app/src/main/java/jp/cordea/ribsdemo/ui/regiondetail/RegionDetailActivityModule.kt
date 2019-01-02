package jp.cordea.ribsdemo.ui.regiondetail

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.ribsdemo.di.ActivityScope
import jp.cordea.ribsdemo.event.BaseSink
import jp.cordea.ribsdemo.event.BaseSource
import jp.cordea.ribsdemo.event.regiondetail.RegionDetailAction
import jp.cordea.ribsdemo.event.regiondetail.RegionDetailDispatcher

@Module
interface RegionDetailActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            RegionDetailActivityBindModule::class,
            RegionDetailAdapterModule::class,
            RegionDetailFragmentModule::class
        ]
    )
    fun contributeRegionDetailActivity(): RegionDetailActivity
}

@Module
interface RegionDetailActivityBindModule {
    @Binds
    fun bindSink(dispatcher: RegionDetailDispatcher): BaseSink<RegionDetailAction>

    @Binds
    fun bindSource(dispatcher: RegionDetailDispatcher): BaseSource<RegionDetailAction>
}
