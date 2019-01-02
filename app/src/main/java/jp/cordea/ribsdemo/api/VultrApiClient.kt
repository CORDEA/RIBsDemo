package jp.cordea.ribsdemo.api

import io.reactivex.Maybe
import jp.cordea.ribsdemo.BuildConfig
import jp.cordea.ribsdemo.KeyManager
import jp.cordea.ribsdemo.api.response.Application
import jp.cordea.ribsdemo.api.response.Region
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VultrApiClient @Inject constructor(
    private val manager: KeyManager,
    private val retrofitBuilder: Retrofit.Builder
) {
    private val service: Maybe<VultrApi>
        get() {
            val key = manager.get() ?: return Maybe.empty()
            var builder = OkHttpClient.Builder()
                .addInterceptor {
                    it.proceed(
                        it.request()
                            .newBuilder()
                            .addHeader("API-Key", key)
                            .build()
                    )
                }

            if (BuildConfig.DEBUG) {
                builder = builder
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BASIC)
                    )
            }

            return Maybe.just(
                retrofitBuilder
                    .client(builder.build())
                    .build()
                    .create(VultrApi::class.java)
            )
        }

    fun getRegions(): Maybe<Map<String, Region>> =
        service.flatMapSingleElement { it.getRegions() }

    fun getApps(): Maybe<Map<String, Application>> =
        service.flatMapSingleElement { it.getApps() }
}
