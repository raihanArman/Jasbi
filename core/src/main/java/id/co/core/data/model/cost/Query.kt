package id.co.core.data.model.cost


import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("courier")
    val courier: String,
    @SerializedName("destination")
    val destination: String,
    @SerializedName("origin")
    val origin: Int,
    @SerializedName("weight")
    val weight: Int
)