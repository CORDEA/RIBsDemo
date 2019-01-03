package jp.cordea.ribsdemo.ui.main

import android.view.ViewGroup
import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.ui.region.RegionBuilder

class MainRouter(
    view: MainView,
    interactor: MainInteractor,
    component: MainBuilder.Component,
    private val regionBuilder: RegionBuilder
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component) {

    fun attachRegion() {
        val view = view.findViewById<ViewGroup>(R.id.container)
        val router = regionBuilder.build(view)
        attachChild(router)
        view.addView(router.view)
    }
}
