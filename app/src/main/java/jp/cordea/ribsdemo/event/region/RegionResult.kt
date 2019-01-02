package jp.cordea.ribsdemo.event.region

import jp.cordea.ribsdemo.api.response.Region

sealed class RegionResult {
    class Success(val regions: Collection<Region>) : RegionResult()
    object Failure : RegionResult()
}
