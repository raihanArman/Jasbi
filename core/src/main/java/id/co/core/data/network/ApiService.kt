package id.co.core.data.network

import id.co.core.data.model.Category
import id.co.core.data.model.CategoryMenu
import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.model.cost.Cost
import id.co.core.data.model.history.History
import id.co.core.data.model.province.Province
import id.co.core.data.model.shipment.Transaction
import id.co.core.data.response.ResponseData
import id.co.core.data.response.ResponseLogin
import id.co.core.data.response.ResponseRegister
import id.co.core.data.response.ResponseTransaction
import retrofit2.http.*

interface ApiService {
    @GET("produk")
    suspend fun getProduct(
        @Query("id_kategori") categoryId: String
    ): ResponseData<List<CategoryMenu>>

    @GET("produk")
    suspend fun getAllProduct(): ResponseData<List<CategoryMenu>>

    @GET("kategori")
    suspend fun getCategory(): ResponseData<List<Category>>

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


    @GET("ongkir/province")
    suspend fun getProvince(): Province

    @GET("ongkir/city")
    suspend fun getCity(
        @Query("id_province") province_id: String
    ): Province

    @FormUrlEncoded
    @POST("ongkir/cost")
    suspend fun getCost(
        @Field("destination") destination: String,
        @Field("weight") weight: String,
    ): Cost

    @POST("transaksi")
    suspend fun addTransaction(
        @Header("token") token: String,
        @Body transaction: Transaction
    ): ResponseTransaction

    @GET("transaksi")
    suspend fun getTransaction(
        @Header("token") token: String
    ): ResponseData<List<History>>

    @GET("pelanggan")
    suspend fun getUser(
        @Header("token") token: String
    ): ResponseData<List<User>>

    @PUT("pelanggan")
    suspend fun editUser(
        @Header("token") token: String,
        @Body user: User
    ): ResponseData<String>

}