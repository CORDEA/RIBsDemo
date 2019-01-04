package jp.cordea.ribsdemo.ui.regiondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.AppModule
import jp.cordea.ribsdemo.R
import jp.cordea.ribsdemo.ui.region.RegionDataSource
import jp.cordea.ribsdemo.ui.region.RegionRemoteDataSource

class RegionDetailBuilder(
    component: ParentComponent
) : ViewBuilder<RegionDetailView, RegionDetailRouter, RegionDetailBuilder.ParentComponent>(component) {

    fun build(parent: ViewGroup): RegionDetailRouter =
        DaggerRegionDetailBuilder_Component.builder()
            .parentComponent(dependency)
            .view(createView(parent))
            .interactor(RegionDetailInteractor())
            .build()
            .regionDetailRouter()

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RegionDetailView =
        inflater.inflate(R.layout.content_region_detail, parentViewGroup, false) as RegionDetailView

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @RegionDetailScope
            fun provideRouter(component: Component, view: RegionDetailView, interactor: RegionDetailInteractor) =
                RegionDetailRouter(view, interactor, component, RegionDetailChildBuilder(component))

            @Provides
            @JvmStatic
            @RegionDetailScope
            fun provideContext(view: RegionDetailView) = view.context
        }

        @Binds
        @RegionDetailScope
        abstract fun bindPresenter(view: RegionDetailView): RegionDetailInteractor.RegionDetailPresenter

        @Binds
        abstract fun bindRegionDataSource(remoteDataSource: RegionRemoteDataSource): RegionDataSource
    }

    @RegionDetailScope
    @dagger.Component(
        modules = [Module::class, AppModule::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<RegionDetailInteractor>,
        BuilderComponent,
        RegionDetailChildBuilder.ParentComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RegionDetailInteractor): Builder

            @BindsInstance
            fun view(view: RegionDetailView): Builder

            fun parentComponent(component: ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun regionDetailRouter(): RegionDetailRouter
    }
}
