package jp.cordea.ribsdemo

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import jp.cordea.ribsdemo.di.ActivityModule
import jp.cordea.ribsdemo.di.AppModule
import jp.cordea.ribsdemo.di.DataSourceModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataSourceModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: Application)
}
