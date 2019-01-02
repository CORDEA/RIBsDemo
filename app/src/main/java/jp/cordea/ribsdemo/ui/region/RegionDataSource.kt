package jp.cordea.ribsdemo.ui.region

import io.reactivex.Maybe
import jp.cordea.ribsdemo.api.response.Region

interface RegionDataSource {
    fun fetchRegion(): Maybe<Collection<Region>>
}
