package jp.cordea.ribsdemo.ui.regiondetail

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import jp.cordea.ribsdemo.api.response.Region
import jp.cordea.ribsdemo.ui.region.RegionRepository
import javax.inject.Inject

@RibInteractor
class RegionDetailInteractor : Interactor<RegionDetailInteractor.RegionDetailPresenter, RegionDetailRouter>() {

    @Inject
    lateinit var presenter: RegionDetailPresenter

    @Inject
    lateinit var repository: RegionRepository

    var initialPosition: Int = 0

    private val compositeDisposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        presenter.setAdapter(RegionDetailPagerAdapter(
            onAttach = { container, item ->
                router.attachChild(container, item)
            },
            onDetach = { container, item ->
                router.detachChild(container, item)
            }
        ))

        repository.fetchRegion(false)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.showItems(it)
                presenter.moveTo(initialPosition)
            }, {
            })
            .addTo(compositeDisposable)
    }

    override fun willResignActive() {
        super.willResignActive()
        compositeDisposable.clear()
    }

    interface RegionDetailPresenter {
        fun setAdapter(adapter: RegionDetailPagerAdapter)
        fun showItems(items: Collection<Region>)
        fun moveTo(position: Int)
    }
}
