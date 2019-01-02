package jp.cordea.ribsdemo.event.login

import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import jp.cordea.ribsdemo.di.ActivityScope
import jp.cordea.ribsdemo.event.BaseDispatcher
import javax.inject.Inject

@ActivityScope
class LoginDispatcher @Inject constructor() : BaseDispatcher<LoginAction> {
    private val _reader = PublishProcessor.create<LoginAction>().toSerialized()
    override val reader: Flowable<LoginAction> = _reader

    override fun dispatch(action: LoginAction) = _reader.onNext(action)
}
