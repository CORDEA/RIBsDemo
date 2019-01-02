package jp.cordea.ribsdemo

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import jp.cordea.ribsdemo.ui.login.LoginInteractor
import javax.inject.Inject

@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>(), LoginInteractor.Listener {
    @Inject
    lateinit var presenter: RootPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachLogin()
    }

    override fun requestMain() {
        router.detachLogin()
        router.attachMain()
    }

    interface RootPresenter
}
