package id.co.core.data.model.history


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailTransaksi(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_detail_transaksi")
    val idDetailTransaksi: String,
    @SerializedName("id_produk")
    val idProduk: String,
    @SerializedName("id_transaksi")
    val idTransaksi: String,
    @SerializedName("jumlah")
    val jumlah: Int,
    @SerializedName("lastUpdate")
    val lastUpdate: String,
    @SerializedName("nama")
    val nama: String
): Parcelable