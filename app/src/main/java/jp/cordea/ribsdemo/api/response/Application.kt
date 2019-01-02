package jp.cordea.ribsdemo.api.response

import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
data class Application(
    @Json(name = "APPID") val id: Int,
    val name: String,
    @Json(name = "short_name") val shortName: String,
    @Json(name = "deploy_name") val deployName: String,
    val surcharge: Float
) : Parcelable
