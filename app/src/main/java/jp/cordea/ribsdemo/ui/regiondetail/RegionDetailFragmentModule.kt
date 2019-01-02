package jp.cordea.ribsdemo.ui.regiondetail

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.ribsdemo.di.FragmentScope

@Module
interface RegionDetailFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector
    fun contributeRegionDetailFragment(): RegionDetailFragment
}
