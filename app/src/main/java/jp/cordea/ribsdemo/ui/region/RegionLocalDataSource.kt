package jp.cordea.ribsdemo.ui.region

import io.reactivex.Maybe
import jp.cordea.ribsdemo.api.response.Region
import javax.inject.Inject

class RegionLocalDataSource @Inject constructor(
) : RegionDataSource {
    private var regions: Collection<Region>? = null

    fun cacheRegion(regions: Collection<Region>) {
        this.regions = regions
    }

    override fun fetchRegion(): Maybe<Collection<Region>> =
        if (regions == null) Maybe.empty() else Maybe.just(regions)
}
