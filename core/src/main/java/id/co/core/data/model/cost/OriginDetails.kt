package id.co.core.data.model.cost


import com.google.gson.annotations.SerializedName

data class OriginDetails(
    @SerializedName("city_id")
    val cityId: String,
    @SerializedName("city_name")
    val cityName: String,
    @SerializedName("postal_code")
    val postalCode: String,
    @SerializedName("province")
    val province: String,
    @SerializedName("province_id")
    val provinceId: String,
    @SerializedName("type")
    val type: String
)