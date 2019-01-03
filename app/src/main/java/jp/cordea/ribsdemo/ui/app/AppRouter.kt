package jp.cordea.ribsdemo.ui.app

import com.uber.rib.core.ViewRouter

class AppRouter(
    view: AppView,
    interactor: AppInteractor,
    component: AppBuilder.Component
) : ViewRouter<AppView, AppInteractor, AppBuilder.Component>(view, interactor, component) {
}
