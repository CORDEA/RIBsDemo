package jp.cordea.ribsdemo.ui.regiondetail

import com.uber.rib.core.ViewRouter

class RegionDetailRouter(
    view: RegionDetailView,
    interactor: RegionDetailInteractor,
    component: RegionDetailBuilder.Component
) : ViewRouter<RegionDetailView, RegionDetailInteractor, RegionDetailBuilder.Component>(view, interactor, component)
