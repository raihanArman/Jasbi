package id.co.core.domain.usecase

import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Category
import id.co.core.data.model.CategoryMenu
import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.model.cost.ResultCost
import id.co.core.data.model.history.History
import id.co.core.data.model.province.Result
import id.co.core.data.model.shipment.Midtrans
import id.co.core.data.model.shipment.Transaction
import id.co.core.data.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface Usecase {
    fun getAllProduct(): Flow<ResponseState<List<CategoryMenu>>>
    fun getProduct(categoryId: String): Flow<ResponseState<List<CategoryMenu>>>
    fun getCategory(): Flow<ResponseState<List<Category>>>
    fun loginUser(email: String, password: String): Flow<ResponseState<User>>
    fun registerUser(
        name: String,
        email: String,
        phoneNumber: String,
        address: String,
        password: String
    ): Flow<ResponseState<String>>
    fun getUser(): Flow<ResponseState<List<User>>>
    fun editUser(user: User): Flow<ResponseState<String>>

    fun insertCart(product: ProductEntitiy): Long
    fun updateQtyCart(qty: Int, idCart: Int)
    fun getProductLocal(): List<ProductEntitiy>
    fun getProductById(id: String): ProductEntitiy
    fun cartProductExist(id: String): Boolean
    fun deleteItemCart(product: ProductEntitiy)
    fun deleteAllCart()

    fun getProvince(): Flow<ResponseState<List<Result>>>
    fun getCity(provinceId: String): Flow<ResponseState<List<Result>>>
    fun getCost(destination: String, weight: String): Flow<ResponseState<List<ResultCost>>>

    fun addTransaction(transaction: Transaction): Flow<ResponseState<Midtrans>>
    fun getTransaction(): Flow<ResponseState<List<History>>>
}