package jp.cordea.ribsdemo.ui.app

import io.reactivex.Maybe
import jp.cordea.ribsdemo.api.VultrApiClient
import jp.cordea.ribsdemo.api.response.Application
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRemoteDataSource @Inject constructor(
    private val apiClient: VultrApiClient
) : AppDataSource {
    override fun fetchApp(): Maybe<Collection<Application>> = apiClient.getApps().map { it.values }
}
