package id.co.core.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    @Expose
    var id: String ?= "",

    @SerializedName("nama_kategori")
    @Expose
    var categoryName: String ?= ""
)