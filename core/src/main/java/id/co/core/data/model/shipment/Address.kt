package id.co.core.data.model.shipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("negara")
    @Expose
    var country: String,

    @SerializedName("Provensi")
    @Expose
    var province: String,

    @SerializedName("kecamatan")
    @Expose
    var subdistrict: String,

    @SerializedName("alamat")
    @Expose
    var address: String
)