package jp.cordea.ribsdemo

import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.ui.login.LoginBuilder

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val loginBuilder: LoginBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {
    fun attachLogin() {
        val loginRouter = loginBuilder.build(view)
        attachChild(loginRouter)
        view.addView(loginRouter.view)
    }
}
