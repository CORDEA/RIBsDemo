package jp.cordea.ribsdemo.event.regiondetail

import jp.cordea.ribsdemo.api.response.Region

sealed class RegionDetailResult {
    class Success(val regions: Collection<Region>) : RegionDetailResult()
    object Failure : RegionDetailResult()
}
