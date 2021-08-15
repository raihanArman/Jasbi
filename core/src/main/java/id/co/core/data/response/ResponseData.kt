package id.co.core.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseData<T>(
    @SerializedName("status_code")
    @Expose
    var status: Int,

    @SerializedName("data")
    @Expose
    var data: T
)