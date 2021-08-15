package id.co.core.data.model.cost


import com.google.gson.annotations.SerializedName

data class ResultCost(
    @SerializedName("code")
    val code: String,
    @SerializedName("costs")
    val costs: List<CostX>,
    @SerializedName("name")
    val name: String
)