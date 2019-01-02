package jp.cordea.ribsdemo.api.response

import android.annotation.SuppressLint
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@SuppressLint("ParcelCreator")
data class Region(
    @Json(name = "DCID") val id: Int,
    val name: String,
    val country: String,
    val continent: String,
    val state: String,
    @Json(name = "ddos_protection") val ddosProtection: Boolean,
    @Json(name = "block_storage") val blockStorage: Boolean,
    @Json(name = "regioncode") val regionCode: String
) : Parcelable
