package jp.cordea.ribsdemo.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.R

class LoginBuilder(
    component: ParentComponent
) : ViewBuilder<LoginView, LoginRouter, LoginBuilder.ParentComponent>(component) {

    fun build(parent: ViewGroup): LoginRouter =
        DaggerLoginBuilder_Component.builder()
            .parentComponent(dependency)
            .view(createView(parent))
            .interactor(LoginInteractor())
            .build()
            .loginRouter()

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LoginView =
        inflater.inflate(R.layout.activity_login, parentViewGroup, false) as LoginView

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @LoginScope
            fun provideRouter(component: Component, view: LoginView, interactor: LoginInteractor) =
                LoginRouter(view, interactor, component)
        }

        @Binds
        @LoginScope
        abstract fun bindPresenter(view: LoginView): LoginInteractor.LoginPresenter
    }

    @LoginScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [LoginBuilder.ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<LoginInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: LoginInteractor): Builder

            @BindsInstance
            fun view(view: LoginView): Builder

            fun parentComponent(component: LoginBuilder.ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun loginRouter(): LoginRouter
    }
}
