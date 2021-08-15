package id.co.core.data.model.shipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Midtrans(
    @SerializedName("token")
    @Expose
    val token: String,

    @SerializedName("redirect_url")
    @Expose
    val redirectUrl: String,
)