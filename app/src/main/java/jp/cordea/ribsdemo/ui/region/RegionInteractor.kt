package jp.cordea.ribsdemo.ui.region

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
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

        repository.fetchRegion(true)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.showItems(it.map { itemFactory.create(RegionItemViewModel.from(it)) })
            }, {
            })
            .addTo(compositeDisposable)
    }

    override fun willResignActive() {
        super.willResignActive()
        compositeDisposable.clear()
    }

    interface RegionPresenter {
        fun showItems(items: List<RegionItem>)
    }
}
