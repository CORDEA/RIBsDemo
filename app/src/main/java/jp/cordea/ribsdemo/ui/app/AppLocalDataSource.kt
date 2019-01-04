package jp.cordea.ribsdemo.ui.app

import io.reactivex.Maybe
import jp.cordea.ribsdemo.api.response.Application
import javax.inject.Inject

class AppLocalDataSource @Inject constructor(
) : AppDataSource {
    private var apps: Collection<Application>? = null

    fun cacheApp(apps: Collection<Application>) {
        this.apps = apps
    }

    override fun fetchApp(): Maybe<Collection<Application>> =
        if (apps == null) Maybe.empty() else Maybe.just(apps)
}
