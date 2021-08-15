package id.co.core.data.model.province

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Province(
    @SerializedName("rajaongkir")
    @Expose
    val rajaongkir: Rajaongkir
)