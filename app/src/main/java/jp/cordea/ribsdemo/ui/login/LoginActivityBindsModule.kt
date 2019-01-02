package jp.cordea.ribsdemo.ui.login

import android.app.Activity
import dagger.Binds
import dagger.Module
import jp.cordea.ribsdemo.event.BaseSink
import jp.cordea.ribsdemo.event.BaseSource
import jp.cordea.ribsdemo.event.login.LoginAction
import jp.cordea.ribsdemo.event.login.LoginDispatcher

@Module
interface LoginActivityBindsModule {
    @Binds
    fun bindActivity(activity: LoginActivity): Activity

    @Binds
    fun bindSink(dispatcher: LoginDispatcher): BaseSink<LoginAction>

    @Binds
    fun bindSource(dispatcher: LoginDispatcher): BaseSource<LoginAction>
}
