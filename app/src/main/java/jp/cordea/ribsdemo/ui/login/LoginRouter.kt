package jp.cordea.ribsdemo.ui.login

import com.uber.rib.core.ViewRouter

class LoginRouter(
    view: LoginView,
    interactor: LoginInteractor,
    component: LoginBuilder.Component
) : ViewRouter<LoginView, LoginInteractor, LoginBuilder.Component>(view, interactor, component)
