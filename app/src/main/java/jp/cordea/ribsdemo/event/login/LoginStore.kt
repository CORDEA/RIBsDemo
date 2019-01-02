package jp.cordea.ribsdemo.event.login

import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.schedulers.Schedulers
import jp.cordea.ribsdemo.KeyManager
import jp.cordea.ribsdemo.di.ActivityScope
import jp.cordea.ribsdemo.event.BaseSource
import javax.inject.Inject

@ActivityScope
class LoginStore @Inject constructor(
    private val keyManager: KeyManager,
    source: BaseSource<LoginAction>
) {
    private val onSubmit =
        source.reader.ofType<LoginAction.Clicked>()
            .withLatestFrom(source.reader.ofType<LoginAction.ChangedText>()) { _, query -> query.query }
            .doOnNext { keyManager.set(it) }
            .map { if (it.isBlank()) LoginResult.Failure else LoginResult.Success }
            .subscribeOn(Schedulers.io())

    fun onReady() = onSubmit.ofType<LoginResult.Success>()
    fun onError() = onSubmit.ofType<LoginResult.Failure>()
}
