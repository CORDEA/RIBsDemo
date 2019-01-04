package jp.cordea.ribsdemo.ui.app

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import jp.cordea.ribsdemo.api.response.Application
import javax.inject.Inject

@RibInteractor
class AppInteractor : Interactor<AppInteractor.AppPresenter, AppRouter>() {

    @Inject
    lateinit var presenter: AppPresenter

    @Inject
    lateinit var repository: AppRepository

    @Inject
    lateinit var itemFactory: AppItem.Factory

    private val compositeDisposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)

        repository.fetchApp(false)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.showItems(it.toItems())
            }, {
            })
            .addTo(compositeDisposable)

        presenter.swipeRefreshes()
            .switchMapMaybe { repository.fetchApp(true) }
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

    private fun Collection<Application>.toItems() =
        map { itemFactory.create(AppItemViewModel.from(it)) }

    interface AppPresenter {
        fun swipeRefreshes(): Observable<Unit>
        fun showItems(items: List<AppItem>)
        fun completeUpdate()
    }
}
