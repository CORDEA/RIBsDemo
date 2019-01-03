package jp.cordea.ribsdemo.ui.region

import io.reactivex.Maybe
import jp.cordea.ribsdemo.api.VultrApiClient
import jp.cordea.ribsdemo.api.response.Region
import javax.inject.Inject

class RegionRemoteDataSource @Inject constructor(
    private val apiClient: VultrApiClient
) : RegionDataSource {
    override fun fetchRegion(): Maybe<Collection<Region>> = apiClient.getRegions().map { it.values }
}
