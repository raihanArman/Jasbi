package id.co.core.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import id.co.core.data.model.User

data class ResponseRegister(
    @SerializedName("status_code")
    @Expose
    var status: Int,

    @SerializedName("token")
    @Expose
    var token: String,

    @SerializedName("msg")
    @Expose
    var msg: String,


)