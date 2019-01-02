package jp.cordea.ribsdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.R

class MainBuilder(
    component: ParentComponent
) : ViewBuilder<MainView, MainRouter, MainBuilder.ParentComponent>(component) {

    fun build(parent: ViewGroup): MainRouter =
        DaggerMainBuilder_Component.builder()
            .parentComponent(dependency)
            .view(createView(parent))
            .interactor(MainInteractor())
            .build()
            .mainRouter()

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MainView =
        inflater.inflate(R.layout.activity_main, parentViewGroup, false) as MainView

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @MainScope
            fun provideRouter(component: Component, view: MainView, interactor: MainInteractor) =
                MainRouter(view, interactor, component)
        }

        @Binds
        @MainScope
        abstract fun bindPresenter(view: MainView): MainInteractor.MainPresenter
    }

    @MainScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [MainBuilder.ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<MainInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: MainInteractor): Builder

            @BindsInstance
            fun view(view: MainView): Builder

            fun parentComponent(component: MainBuilder.ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun mainRouter(): MainRouter
    }
}
