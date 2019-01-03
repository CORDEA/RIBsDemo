package jp.cordea.ribsdemo.ui.app

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.R

class AppBuilder(
    component: ParentComponent
) : ViewBuilder<AppView, AppRouter, AppBuilder.ParentComponent>(component) {

    fun build(parent: ViewGroup): AppRouter =
        DaggerAppBuilder_Component.builder()
            .parentComponent(dependency)
            .view(createView(parent))
            .interactor(AppInteractor())
            .build()
            .appRouter()

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): AppView =
        inflater.inflate(R.layout.fragment_app, parentViewGroup, false) as AppView

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @AppScope
            fun provideRouter(component: Component, view: AppView, interactor: AppInteractor) =
                AppRouter(view, interactor, component)
        }

        @Binds
        @AppScope
        abstract fun bindPresenter(view: AppView): AppInteractor.AppPresenter

        @Binds
        abstract fun bindAppDataSource(remoteDataSource: AppRemoteDataSource): AppDataSource
    }

    @AppScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [AppBuilder.ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<AppInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: AppInteractor): Builder

            @BindsInstance
            fun view(view: AppView): Builder

            fun parentComponent(component: AppBuilder.ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun appRouter(): AppRouter
    }
}
