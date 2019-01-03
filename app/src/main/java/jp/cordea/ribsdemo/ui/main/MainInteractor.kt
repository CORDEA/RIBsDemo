package jp.cordea.ribsdemo.ui.main

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class MainInteractor : Interactor<MainInteractor.MainPresenter, MainRouter>() {

    @Inject
    lateinit var presenter: MainPresenter

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
    }

    override fun handleBackPress(): Boolean {
        if (presenter.handleDrawer()) {
            return true
        }
        return super.handleBackPress()
    }

    interface MainPresenter {
        fun handleDrawer(): Boolean
    }
}
