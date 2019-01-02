package jp.cordea.ribsdemo.api

import io.reactivex.Single
import jp.cordea.ribsdemo.api.response.Application
import jp.cordea.ribsdemo.api.response.Region
import retrofit2.http.GET

interface VultrApi {
    @GET("v1/regions/list")
    fun getRegions(): Single<Map<String, Region>>

    @GET("v1/app/list")
    fun getApps(): Single<Map<String, Application>>
}
