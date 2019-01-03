package jp.cordea.ribsdemo.ui.region

import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import jp.cordea.ribsdemo.api.response.Region
import javax.inject.Inject

class RegionRepository @Inject constructor(
    private val dataSource: RegionDataSource,
    private val localDataSource: RegionLocalDataSource
) {
    fun fetchRegion(forceFetch: Boolean): Maybe<Collection<Region>> =
        if (forceFetch) {
            dataSource.fetchRegion()
        } else {
            localDataSource.fetchRegion()
                .switchIfEmpty(
                    dataSource.fetchRegion()
                        .doOnSuccess { localDataSource.cacheRegion(it) }
                )
        }.subscribeOn(Schedulers.io())
}
