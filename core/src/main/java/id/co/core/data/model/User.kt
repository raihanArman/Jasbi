package id.co.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("id_pelanggan")
    @Expose
    var idUser: String,

    @SerializedName("nama")
    @Expose
    var name: String,

    @SerializedName("email")
    @Expose
    var email: String,

    @SerializedName("nohp")
    @Expose
    var phoneNumber: String,

    @SerializedName("alamat")
    @Expose
    var alamat: String,

    @SerializedName("password")
    @Expose
    var password: String
): Parcelable