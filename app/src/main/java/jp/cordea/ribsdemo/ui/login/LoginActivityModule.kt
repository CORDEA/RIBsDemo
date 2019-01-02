package jp.cordea.ribsdemo.ui.login

import dagger.Module
import dagger.android.ContributesAndroidInjector
import jp.cordea.ribsdemo.di.ActivityScope

@Module
interface LoginActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            LoginActivityBindsModule::class
        ]
    )
    fun contributeLoginActivity(): LoginActivity
}
