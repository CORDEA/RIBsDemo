package jp.cordea.ribsdemo.ui.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.ribsdemo.di.FragmentScope

@Module
interface AppFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            AppFragmentBindsModule::class
        ]
    )
    fun contributeAppFragment(): AppFragment
}
