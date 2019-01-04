package jp.cordea.ribsdemo.ui.regiondetail

import android.view.View
import android.view.ViewGroup
import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.api.response.Region

class RegionDetailRouter(
    view: RegionDetailView,
    interactor: RegionDetailInteractor,
    component: RegionDetailBuilder.Component,
    private val builder: RegionDetailChildBuilder
) : ViewRouter<RegionDetailView, RegionDetailInteractor, RegionDetailBuilder.Component>(view, interactor, component) {
    private val routers = mutableMapOf<String, RegionDetailChildRouter>()

    fun attachChild(parent: ViewGroup, region: Region): View {
        val router = builder.build(parent)
        router.interactor.region = region
        attachChild(router, region.name)
        parent.addView(router.view)
        routers[region.name] = router
        return router.view
    }

    fun detachChild(parent: ViewGroup, region: Region) {
        val router = routers.getValue(region.name)
        detachChild(router)
        parent.removeView(router.view)
        routers.remove(region.name)
    }
}
