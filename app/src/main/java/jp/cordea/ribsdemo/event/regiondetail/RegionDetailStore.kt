package jp.cordea.ribsdemo.event.regiondetail

import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import jp.cordea.ribsdemo.di.ActivityScope
import jp.cordea.ribsdemo.event.BaseSource
import jp.cordea.ribsdemo.ui.region.RegionRepository
import javax.inject.Inject

@ActivityScope
class RegionDetailStore @Inject constructor(
    private val repository: RegionRepository,
    source: BaseSource<RegionDetailAction>
) {
    private val fetch =
        source.reader.ofType<RegionDetailAction>()
            .flatMapSingle {
                repository.fetchRegion(false)
                    .map<RegionDetailResult> { RegionDetailResult.Success(it) }
                    .switchIfEmpty(Single.just(RegionDetailResult.Failure))
            }
            .share()

    fun onReady() = fetch.ofType<RegionDetailResult.Success>().map { it.regions }
    fun onError() = fetch.ofType<RegionDetailResult.Failure>().map { Unit }
}
