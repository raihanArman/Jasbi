package id.co.core.data.model.cost


import com.google.gson.annotations.SerializedName

data class CostX(
    @SerializedName("cost")
    val cost: List<CostXX>,
    @SerializedName("description")
    val description: String,
    @SerializedName("service")
    val service: String
)