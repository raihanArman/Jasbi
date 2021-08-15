package id.co.core.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryMenu(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("nama_kategori")
    @Expose
    var categoryName: String ?= "",

    @SerializedName("dataProduk")
    @Expose
    var product: List<Menu>
): Parcelable