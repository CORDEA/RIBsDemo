package jp.cordea.ribsdemo.ui.main

import android.view.ViewGroup
import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.ui.app.AppBuilder
import jp.cordea.ribsdemo.ui.app.AppRouter
import jp.cordea.ribsdemo.ui.region.RegionBuilder
import jp.cordea.ribsdemo.ui.region.RegionRouter

class MainRouter(
    view: MainView,
    interactor: MainInteractor,
    component: MainBuilder.Component,
    private val regionBuilder: RegionBuilder,
    private val appBuilder: AppBuilder
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {
    private var regionRouter: RegionRouter? = null
    private var appRouter: AppRouter? = null

    private val container get() = view.findViewById<ViewGroup>(R.id.container)

    fun attachRegion() {
        val router = regionBuilder.build(container)
        attachChild(router)
        container.addView(router.view)
        this.regionRouter = router
    }

    fun detachRegion() {
        val router = regionRouter ?: return
        detachChild(router)
        container.removeView(router.view)
        regionRouter = null
    }

    fun attachApp() {
        val router = appBuilder.build(container)
        attachChild(router)
        container.addView(router.view)
        this.appRouter = router
    }

    fun detachApp() {
        val router = appRouter ?: return
        detachChild(router)
        container.removeView(router.view)
        appRouter = null
    }
}
