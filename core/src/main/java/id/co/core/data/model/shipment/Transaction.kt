package id.co.core.data.model.shipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.co.core.data.db.entities.ProductEntitiy

data class Transaction(
    @SerializedName("produk")
    @Expose
    val listProduk: List<ProductEntitiy>,

    @SerializedName("metode")
    @Expose
    val method: Int,

    @SerializedName("pengiriman")
    @Expose
    val ship: Ship,

    @SerializedName("alamat")
    @Expose
    val address: Address,

)