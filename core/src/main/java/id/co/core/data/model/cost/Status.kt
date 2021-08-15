package id.co.core.data.model.cost


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("code")
    val code: Int,
    @SerializedName("description")
    val description: String
)