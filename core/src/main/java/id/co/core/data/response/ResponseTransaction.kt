package id.co.core.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.co.core.data.model.shipment.Midtrans

data class ResponseTransaction(
    @SerializedName("status_code")
    @Expose
    var status: Int,

    @SerializedName("msg")
    @Expose
    var message: String,

    @SerializedName("midtrans_token")
    @Expose
    var midtransToken: Midtrans,
)