package id.co.core.data.network

import id.co.core.data.model.Menu
import id.co.core.data.response.ResponseData
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("produk")
    suspend fun getProduct(): ResponseData<List<Menu>>
}