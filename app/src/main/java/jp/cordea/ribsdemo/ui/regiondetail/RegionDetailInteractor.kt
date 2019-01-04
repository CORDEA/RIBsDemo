package jp.cordea.ribsdemo.ui.regiondetail

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class RegionDetailInteractor : Interactor<RegionDetailInteractor.RegionDetailPresenter, RegionDetailRouter>() {

    @Inject
    lateinit var presenter: RegionDetailPresenter

    lateinit var builder: RegionDetailChildBuilder

    var initialPosition: Int = 0

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.setAdapter(RegionDetailPagerAdapter(builder))
        presenter.moveTo(initialPosition)
    }

    interface RegionDetailPresenter {
        fun setAdapter(adapter: RegionDetailPagerAdapter)
        fun moveTo(position: Int)
    }
}
