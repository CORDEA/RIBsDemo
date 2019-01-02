package jp.cordea.ribsdemo.di

import dagger.Binds
import dagger.Module
import jp.cordea.ribsdemo.ui.app.AppDataSource
import jp.cordea.ribsdemo.ui.app.AppRemoteDataSource
import jp.cordea.ribsdemo.ui.region.RegionDataSource
import jp.cordea.ribsdemo.ui.region.RegionRemoteDataSource

@Module
interface DataSourceModule {
    @Binds
    fun bindRegionDataSource(remoteDataSource: RegionRemoteDataSource): RegionDataSource

    @Binds
    fun bindAppDataSource(remoteDataSource: AppRemoteDataSource): AppDataSource
}
