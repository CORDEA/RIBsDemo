package jp.cordea.ribsdemo.event.region

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import jp.cordea.ribsdemo.di.FragmentScope
import jp.cordea.ribsdemo.event.BaseSource
import jp.cordea.ribsdemo.ui.region.RegionRepository
import javax.inject.Inject

@FragmentScope
class RegionStore @Inject constructor(
    private val repository: RegionRepository,
    private val source: BaseSource<RegionAction>
) {
    private val init = source.reader.ofType<RegionAction.Init>().map { false }
    private val refresh = source.reader.ofType<RegionAction.Refresh>().map { true }

    private val fetch =
        Flowable.merge(init, refresh)
            .flatMapSingle {
                repository.fetchRegion(it)
                    .map<RegionResult> { RegionResult.Success(it) }
                    .switchIfEmpty(Single.just(RegionResult.Failure))
            }
            .share()

    fun onReady() = fetch.ofType<RegionResult.Success>().map { it.regions }
    fun onError() = fetch.ofType<RegionResult.Failure>().map { Unit }
    fun onClickedItem() = source.reader.ofType<RegionAction.ClickedItem>().map { it.position }
}
