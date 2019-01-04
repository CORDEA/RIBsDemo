package jp.cordea.ribsdemo.ui.regiondetail

import com.uber.rib.core.ViewRouter

class RegionDetailChildRouter(
    view: RegionDetailChildView,
    interactor: RegionDetailChildInteractor,
    component: RegionDetailChildBuilder.Component
) : ViewRouter<RegionDetailChildView, RegionDetailChildInteractor, RegionDetailChildBuilder.Component>(
    view,
    interactor,
    component
)
