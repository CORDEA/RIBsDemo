package jp.cordea.ribsdemo

import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.ui.login.LoginBuilder
import jp.cordea.ribsdemo.ui.login.LoginRouter
import jp.cordea.ribsdemo.ui.main.MainBuilder

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val loginBuilder: LoginBuilder,
    private val mainBuilder: MainBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {
    private var loginRouter: LoginRouter? = null

    fun attachLogin() {
        loginRouter = loginBuilder.build(view).also {
            attachChild(it)
            view.addView(it.view)
        }
    }

    fun detachLogin() {
        val router = loginRouter ?: return
        detachChild(router)
        view.removeView(router.view)
        loginRouter = null
    }

    fun attachMain() {
        val router = mainBuilder.build(view)
        attachChild(router)
        view.addView(router.view)
    }
}
