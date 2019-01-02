package jp.cordea.ribsdemo.ui.app

import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import jp.cordea.ribsdemo.api.response.Application
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val dataSource: AppDataSource,
    private val localDataSource: AppLocalDataSource
) {
    fun fetchApp(forceFetch: Boolean): Maybe<Collection<Application>> =
        if (forceFetch) {
            dataSource.fetchApp()
        } else {
            localDataSource.fetchApp()
                .switchIfEmpty(
                    dataSource.fetchApp()
                        .doOnSuccess { localDataSource.cacheApp(it) }
                )
        }.subscribeOn(Schedulers.io())
}
