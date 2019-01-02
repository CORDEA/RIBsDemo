package jp.cordea.ribsdemo.ui.region

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.ribsdemo.di.FragmentScope

@Module
interface RegionFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            RegionFragmentBindsModule::class
        ]
    )
    fun contributeRegionFragment(): RegionFragment
}
