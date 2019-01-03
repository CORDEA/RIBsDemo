package jp.cordea.ribsdemo.ui.region

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import jp.cordea.ribsdemo.R
import retrofit2.Retrofit

class RegionBuilder(
    component: ParentComponent
) : ViewBuilder<RegionView, RegionRouter, RegionBuilder.ParentComponent>(component) {

    fun build(parent: ViewGroup): RegionRouter =
        DaggerRegionBuilder_Component.builder()
            .parentComponent(dependency)
            .view(createView(parent))
            .interactor(RegionInteractor())
            .build()
            .regionRouter()

    override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RegionView =
        inflater.inflate(R.layout.region_ribs, parentViewGroup, false) as RegionView


    interface ParentComponent {
        fun context(): Context
        fun retrofitBuilder(): Retrofit.Builder
    }

    @dagger.Module
    abstract class Module {
        @dagger.Module
        companion object {
            @Provides
            @JvmStatic
            @RegionScope
            fun provideRouter(component: Component, view: RegionView, interactor: RegionInteractor) =
                RegionRouter(view, interactor, component)
        }

        @Binds
        @RegionScope
        abstract fun bindPresenter(view: RegionView): RegionInteractor.RegionPresenter

        @Binds
        abstract fun bindRegionDataSource(remoteDataSource: RegionRemoteDataSource): RegionDataSource
    }

    @RegionScope
    @dagger.Component(
        modules = [Module::class],
        dependencies = [RegionBuilder.ParentComponent::class]
    )
    interface Component : InteractorBaseComponent<RegionInteractor>, BuilderComponent {
        @dagger.Component.Builder
        interface Builder {
            @BindsInstance
            fun interactor(interactor: RegionInteractor): Builder

            @BindsInstance
            fun view(view: RegionView): Builder

            fun parentComponent(component: RegionBuilder.ParentComponent): Builder

            fun build(): Component
        }
    }

    interface BuilderComponent {
        fun regionRouter(): RegionRouter
    }
}
