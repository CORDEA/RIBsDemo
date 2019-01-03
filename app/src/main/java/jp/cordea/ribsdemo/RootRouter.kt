package jp.cordea.ribsdemo

import android.content.Intent
import com.uber.rib.core.ViewRouter
import jp.cordea.ribsdemo.ui.login.LoginBuilder
import jp.cordea.ribsdemo.ui.login.LoginRouter
import jp.cordea.ribsdemo.ui.main.MainActivity

class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val loginBuilder: LoginBuilder
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

    fun navigateToMain() {
        val context = view.context
        context.startActivity(Intent(context, MainActivity::class.java))
    }
}
