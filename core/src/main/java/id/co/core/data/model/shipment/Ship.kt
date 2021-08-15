package id.co.core.data.model.shipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Ship(
    @SerializedName("jasa")
    @Expose
    var jasa: String,

    @SerializedName("harga_pengiriman")
    @Expose
    var costs: Int,
)