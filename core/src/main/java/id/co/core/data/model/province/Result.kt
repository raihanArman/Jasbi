package id.co.core.data.model.province

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("city_id")
    @Expose
    val city_id: String,

    @SerializedName("city_name")
    @Expose
    val city_name: String,

    @SerializedName("postal_code")
    @Expose
    val postal_code: String,

    @SerializedName("province")
    @Expose
    val province: String,

    @SerializedName("province_id")
    @Expose
    val province_id: String,

    @SerializedName("type")
    @Expose
    val type: String
)