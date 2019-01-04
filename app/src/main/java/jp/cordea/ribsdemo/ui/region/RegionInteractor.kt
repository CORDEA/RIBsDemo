package jp.cordea.ribsdemo.ui.region

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import jp.cordea.ribsdemo.api.response.Region
import javax.inject.Inject

@RibInteractor
class RegionInteractor : Interactor<RegionInteractor.RegionPresenter, RegionRouter>() {

    @Inject
    lateinit var presenter: RegionPresenter

    @Inject
    lateinit var repository: RegionRepository

    @Inject
    lateinit var itemFactory: RegionItem.Factory

    private val compositeDisposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        repository.fetchRegion(false)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.showItems(it.toItems())
            }, {
            })
            .addTo(compositeDisposable)

        presenter.swipeRefreshes()
            .switchMapMaybe { repository.fetchRegion(true) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.showItems(it.toItems())
                presenter.completeUpdate()
            }, {
            })
            .addTo(compositeDisposable)
    }

    override fun willResignActive() {
        super.willResignActive()
        compositeDisposable.clear()
    }

    private fun Collection<Region>.toItems() =
        map { itemFactory.create(RegionItemViewModel.from(it)) }

    interface RegionPresenter {
        fun swipeRefreshes(): Observable<Unit>
        fun showItems(items: List<RegionItem>)
        fun completeUpdate()
    }
}
