package id.co.core.data.network

import id.co.core.data.model.Menu
import id.co.core.data.response.ResponseData
import id.co.core.data.response.ResponseLogin
import id.co.core.data.response.ResponseRegister
import retrofit2.http.*

interface ApiService {
    @GET("produk")
    suspend fun getProduct(): ResponseData<List<Menu>>

    @FormUrlEncoded
    @POST("auth/login/pelanggan")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): ResponseLogin

    @FormUrlEncoded
    @POST("pelanggan")
    suspend fun registerUser(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("nohp") nohp: String,
        @Field("alamat") alamat: String,
        @Field("password") password: String
    ): ResponseRegister

}