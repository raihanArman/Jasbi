package id.co.core.data.model.province

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rajaongkir(
    @SerializedName("results")
    @Expose
    val results: List<Result>,

    @SerializedName("status")
    @Expose
    val status: Status
)