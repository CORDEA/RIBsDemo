package jp.cordea.ribsdemo.ui.region

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor

@RibInteractor
class RegionInteractor : Interactor<RegionInteractor.RegionPresenter, RegionRouter>() {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
    }

    interface RegionPresenter
}
