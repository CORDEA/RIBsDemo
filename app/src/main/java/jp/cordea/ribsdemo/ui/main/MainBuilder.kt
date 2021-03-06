package jp.cordea.ribsdemo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.AppModule
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.ui.app.AppBuilder
import jp.cordea.ribsdemo.ui.region.RegionBuilder

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
        inflater.inflate(R.layout.main_ribs, parentViewGroup, false) as MainView

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @MainScope
            fun provideRouter(component: Component, view: MainView, interactor: MainInteractor) =
                MainRouter(view, interactor, component, RegionBuilder(component), AppBuilder(component))

            @Provides
            @JvmStatic
            @MainScope
            fun provideContext(view: MainView) = view.context
        }

        @Binds
        @MainScope
        abstract fun bindPresenter(view: MainView): MainInteractor.MainPresenter
    }

    @MainScope
    @dagger.Component(
        modules = [Module::class, AppModule::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<MainInteractor>,
        BuilderComponent,
        RegionBuilder.ParentComponent,
        AppBuilder.ParentComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: MainInteractor): Builder

            @BindsInstance
            fun view(view: MainView): Builder

            fun parentComponent(component: ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun mainRouter(): MainRouter
    }
}
