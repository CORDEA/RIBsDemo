package jp.cordea.ribsdemo

import com.uber.rib.core.ViewRouter

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component)
