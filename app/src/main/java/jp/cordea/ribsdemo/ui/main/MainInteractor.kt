package jp.cordea.ribsdemo.ui.main

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import jp.cordea.ribsdemo.R
import javax.inject.Inject

@RibInteractor
class MainInteractor : Interactor<MainInteractor.MainPresenter, MainRouter>() {

    @Inject
    lateinit var presenter: MainPresenter

    private val compositeDisposable = CompositeDisposable()

    override fun didBecomeActive(savedInstanceState: Bundle?) {
        super.didBecomeActive(savedInstanceState)
        router.attachRegion()

        presenter.navigationItemClicks()
            .subscribeBy {
                when (it) {
                    R.id.nav_region -> {
                        router.detachRegion()
                        router.attachRegion()
                    }
                    R.id.nav_app -> {
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    override fun willResignActive() {
        super.willResignActive()
        compositeDisposable.clear()
    }

    override fun handleBackPress(): Boolean {
        if (presenter.handleDrawer()) {
            return true
        }
        return super.handleBackPress()
    }

    interface MainPresenter {
        fun navigationItemClicks(): Observable<Int>
        fun handleDrawer(): Boolean
    }
}
