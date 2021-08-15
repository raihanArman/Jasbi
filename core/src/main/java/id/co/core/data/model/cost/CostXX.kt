package id.co.core.data.model.cost


import com.google.gson.annotations.SerializedName

data class CostXX(
    @SerializedName("etd")
    val etd: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("value")
    val value: Int
)