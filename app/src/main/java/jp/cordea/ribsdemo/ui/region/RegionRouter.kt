package jp.cordea.ribsdemo.ui.region

import com.uber.rib.core.ViewRouter

class RegionRouter(
    view: RegionView,
    interactor: RegionInteractor,
    component: RegionBuilder.Component
) : ViewRouter<RegionView, RegionInteractor, RegionBuilder.Component>(view, interactor, component)
