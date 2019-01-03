package jp.cordea.ribsdemo.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.ribsdemo.di.ActivityScope
import jp.cordea.ribsdemo.ui.app.AppFragmentModule

@Module
interface MainActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            AppFragmentModule::class
        ]
    )
    fun contributeMainActivity(): MainActivity
}
