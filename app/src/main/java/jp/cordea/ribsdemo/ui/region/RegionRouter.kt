package jp.cordea.ribsdemo.ui.region

import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.ui.regiondetail.RegionDetailActivity

class RegionRouter(
    view: RegionView,
    interactor: RegionInteractor,
    component: RegionBuilder.Component
) : ViewRouter<RegionView, RegionInteractor, RegionBuilder.Component>(view, interactor, component) {
    fun navigateToDetail(position: Int) {
        val context = view.context
        context.startActivity(RegionDetailActivity.createIntent(context, position))
    }
}
