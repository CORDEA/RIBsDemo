package jp.cordea.ribsdemo.ui.login

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.withLatestFrom
import javax.inject.Inject

@RibInteractor
class LoginInteractor : Interactor<LoginInteractor.LoginPresenter, LoginRouter>() {

    @Inject
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var listener: Listener

    private val compositeDisposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        presenter.clicks()
            .withLatestFrom(presenter.apiKeyChanges()) { _, key -> key }
            .filter { it.isNotBlank() }
            .subscribeBy { listener.requestMain() }
            .addTo(compositeDisposable)
    }

    override fun willResignActive() {
        super.willResignActive()
        compositeDisposable.clear()
    }

    interface LoginPresenter {
        fun apiKeyChanges(): Observable<String>
        fun clicks(): Observable<Unit>
    }

    interface Listener {
        fun requestMain()
    }
}
