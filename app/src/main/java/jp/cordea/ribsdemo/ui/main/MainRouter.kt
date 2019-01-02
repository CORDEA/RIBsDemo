package jp.cordea.ribsdemo.ui.main

import com.uber.rib.core.ViewRouter

class MainRouter(
    view: MainView,
    interactor: MainInteractor,
    component: MainBuilder.Component
) : ViewRouter<MainView, MainInteractor, MainBuilder.Component>(view, interactor, component)
