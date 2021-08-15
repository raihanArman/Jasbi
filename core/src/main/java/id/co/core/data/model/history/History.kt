package id.co.core.data.model.history


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class History(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("detail_transaksi")
    val detailTransaksi: List<DetailTransaksi>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_pelanggan")
    val idPelanggan: String,
    @SerializedName("id_transaksi")
    val idTransaksi: String,
    @SerializedName("lastUpdate")
    val lastUpdate: String,
    @SerializedName("metode_pembayaran")
    val metodePembayaran: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nohp")
    val nohp: String,
    @SerializedName("status_pembayaran")
    val statusPembayaran: Int,
    @SerializedName("tgl_transaksi")
    val tglTransaksi: Date ?= Date(),
    @SerializedName("total_transaksi")
    val totalTransaksi: Int
): Parcelable