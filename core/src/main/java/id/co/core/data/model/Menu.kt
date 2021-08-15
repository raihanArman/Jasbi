package id.co.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("id_produk")
    @Expose
    var idProduk: String ?= "",

    @SerializedName("nama")
    @Expose
    var nama: String ?= "",

    @SerializedName("deskripsi")
    @Expose
    var deskripsi: String ?= "",

    @SerializedName("satuan")
    @Expose
    var satuan: String ?= "",

    @SerializedName("stok")
    @Expose
    var stok: String ?= "",

    @SerializedName("harga")
    @Expose
    var harga: String ?= "",

    @SerializedName("img")
    @Expose
    var image: String ?= "",

    @SerializedName("rating")
    @Expose
    var rating: String ?= ""
): Parcelable