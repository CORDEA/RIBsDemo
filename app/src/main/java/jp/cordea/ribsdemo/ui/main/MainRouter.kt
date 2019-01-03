package jp.cordea.ribsdemo.ui.main

import android.view.ViewGroup
import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.ui.region.RegionBuilder
import jp.cordea.ribsdemo.ui.region.RegionRouter

class MainRouter(
    view: MainView,
    interactor: MainInteractor,
    component: MainBuilder.Component,
    private val regionBuilder: RegionBuilder
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {
    private var regionRouter: RegionRouter? = null

    fun attachRegion() {
        val view = view.findViewById<ViewGroup>(R.id.container)
        val router = regionBuilder.build(view)
        attachChild(router)
        view.addView(router.view)
        this.regionRouter = router
    }

    fun detachRegion() {
        val router = regionRouter ?: return
        val view = view.findViewById<ViewGroup>(R.id.container)
        detachChild(router)
        view.removeView(router.view)
        regionRouter = null
    }
}
