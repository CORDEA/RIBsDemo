package jp.cordea.ribsdemo.ui.regiondetail

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import jp.cordea.ribsdemo.api.response.Region
import javax.inject.Inject

@RibInteractor
class RegionDetailChildInteractor :
    Interactor<RegionDetailChildInteractor.RegionDetailChildPresenter, RegionDetailChildRouter>() {
    @Inject
    lateinit var presenter: RegionDetailChildPresenter

    lateinit var region: Region

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.setItem(region)
    }

    interface RegionDetailChildPresenter {
        fun setItem(region: Region)
    }
}
