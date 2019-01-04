package jp.cordea.ribsdemo.ui.regiondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.R

class RegionDetailChildBuilder(
    component: ParentComponent
) : ViewBuilder<RegionDetailChildView, RegionDetailChildRouter, RegionDetailChildBuilder.ParentComponent>(component) {

    fun build(parent: ViewGroup): RegionDetailChildRouter =
        DaggerRegionDetailChildBuilder_Component.builder()
            .parentComponent(dependency)
            .view(createView(parent))
            .interactor(RegionDetailChildInteractor())
            .build()
            .regionDetailRouter()

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RegionDetailChildView =
        inflater.inflate(R.layout.fragment_region_detail, parentViewGroup, false) as RegionDetailChildView

    interface ParentComponent

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @RegionDetailChildScope
            fun provideRouter(
                component: Component,
                view: RegionDetailChildView,
                interactor: RegionDetailChildInteractor
            ) = RegionDetailChildRouter(view, interactor, component)
        }

        @Binds
        @RegionDetailChildScope
        abstract fun bindPresenter(view: RegionDetailChildView): RegionDetailChildInteractor.RegionDetailChildPresenter
    }

    @RegionDetailChildScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<RegionDetailChildInteractor>,
        BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RegionDetailChildInteractor): Builder

            @BindsInstance
            fun view(view: RegionDetailChildView): Builder

            fun parentComponent(component: ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun regionDetailRouter(): RegionDetailChildRouter
    }
}
