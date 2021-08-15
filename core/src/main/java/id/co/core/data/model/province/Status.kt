package id.co.core.data.model.province

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("code")
    @Expose
    val code: Int,

    @SerializedName("description")
    @Expose
    val description: String
)