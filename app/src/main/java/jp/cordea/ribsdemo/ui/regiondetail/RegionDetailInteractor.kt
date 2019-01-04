package jp.cordea.ribsdemo.ui.regiondetail

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor

@RibInteractor
class RegionDetailInteractor : Interactor<RegionDetailInteractor.RegionDetailPresenter, RegionDetailRouter>() {

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
    }

    interface RegionDetailPresenter
}
