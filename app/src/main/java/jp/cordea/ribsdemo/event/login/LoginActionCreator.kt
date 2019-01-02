package jp.cordea.ribsdemo.event.login

import jp.cordea.ribsdemo.di.ActivityScope
import jp.cordea.ribsdemo.event.BaseSink
import javax.inject.Inject

@ActivityScope
class LoginActionCreator @Inject constructor(
    private val sink: BaseSink<LoginAction>
) {
    fun changedText(query: String) = sink.dispatch(LoginAction.ChangedText(query))

    fun clicked() = sink.dispatch(LoginAction.Clicked)
}
