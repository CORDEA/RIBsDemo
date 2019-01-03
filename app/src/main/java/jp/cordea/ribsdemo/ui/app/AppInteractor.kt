package jp.cordea.ribsdemo.ui.app

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor

@RibInteractor
class AppInteractor : Interactor<AppInteractor.AppPresenter, AppRouter>() {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
    }

    interface AppPresenter
}
