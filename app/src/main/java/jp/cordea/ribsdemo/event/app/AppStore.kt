package jp.cordea.ribsdemo.event.app

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.rxkotlin.ofType
import jp.cordea.ribsdemo.di.FragmentScope
import jp.cordea.ribsdemo.event.BaseSource
import jp.cordea.ribsdemo.ui.app.AppRepository
import javax.inject.Inject

@FragmentScope
class AppStore @Inject constructor(
    private val repository: AppRepository,
    source: BaseSource<AppAction>
) {
    private val init = source.reader.ofType<AppAction.Init>().map { false }
    private val refresh = source.reader.ofType<AppAction.Refresh>().map { true }

    private val fetch =
        Flowable.merge(init, refresh)
            .flatMapSingle {
                repository.fetchApp(it)
                    .map<AppResult> { AppResult.Success(it) }
                    .switchIfEmpty(Single.just(AppResult.Failure))
            }
            .share()

    fun onReady() = fetch.ofType<AppResult.Success>().map { it.apps }
    fun onError() = fetch.ofType<AppResult.Failure>().map { Unit }
}
