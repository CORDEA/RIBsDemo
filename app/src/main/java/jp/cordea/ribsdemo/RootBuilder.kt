package jp.cordea.ribsdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.ui.login.LoginBuilder

class RootBuilder(
    dependency: ParentComponent
) : ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

    fun build(parentViewGroup: ViewGroup): RootRouter =
        DaggerRootBuilder_Component.builder()
            .parentComponent(dependency)
            .view(createView(parentViewGroup))
            .interactor(RootInteractor())
            .build()
            .rootRouter()

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView =
        inflater.inflate(R.layout.root_rib, parentViewGroup, false) as RootView

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @RootScope
            fun provideRouter(component: Component, view: RootView, interactor: RootInteractor) =
                RootRouter(view, interactor, component, LoginBuilder(component))
        }

        @Binds
        @RootScope
        abstract fun bindPresenter(view: RootView): RootInteractor.RootPresenter
    }

    @RootScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<RootInteractor>, BuilderComponent, LoginBuilder.ParentComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RootInteractor): Builder

            @BindsInstance
            fun view(view: RootView): Builder

            fun parentComponent(component: ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun rootRouter(): RootRouter
    }
}
