package jp.cordea.ribsdemo.ui.login

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class LoginInteractor : Interactor<LoginInteractor.LoginPresenter, LoginRouter>() {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
    }

    interface LoginPresenter
}
