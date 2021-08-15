package id.co.core.data.repositories

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
import id.co.core.data.repositories.local.LocalDataSource
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.data.response.ResponseState
import id.co.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
): Repository{
    override fun getAllProduct(): Flow<ResponseState<List<CategoryMenu>>> {
        return remote.getAllProduct()
    }

    override fun getProduct(categoryId: String): Flow<ResponseState<List<CategoryMenu>>> {
        return remote.getProduct(categoryId)
    }

    override fun getCategory(): Flow<ResponseState<List<Category>>> {
        return remote.getCategory()
    }

    override fun loginUser(email: String, password: String): Flow<ResponseState<User>> {
        return remote.loginUser(email, password)
    }

    override fun registerUser(
        name: String,
        email: String,
        phoneNumber: String,
        address: String,
        password: String
    ): Flow<ResponseState<String>> {
        return remote.registerUser(name, email, phoneNumber, address, password)
    }

    override fun getUser(): Flow<ResponseState<List<User>>> {
        return remote.getUser()
    }

    override fun editUser(user: User): Flow<ResponseState<String>> {
        return remote.editUser(user)
    }

    override fun insertCart(product: ProductEntitiy): Long {
        return local.insertCart(product)
    }

    override fun updateQtyCart(qty: Int, idCart: Int) {
        return local.updateQtyCart(qty, idCart)
    }

    override fun getProductLocal(): List<ProductEntitiy> {
        return local.getProductLocal()
    }

    override fun getProductById(id: String): ProductEntitiy {
        return local.getProductById(id)
    }

    override fun cartProductExist(id: String): Boolean {
        return local.cartProductExist(id)
    }

    override fun deleteAllCart() {
        return local.deleteAllCart()
    }

    override fun deleteItemCart(product: ProductEntitiy) {
        return local.deleteItemCart(product)
    }

    override fun getProvince(): Flow<ResponseState<List<Result>>> {
        return remote.getProvince()
    }

    override fun getCity(provinceId: String): Flow<ResponseState<List<Result>>> {
        return remote.getCity(provinceId)
    }

    override fun getCost(destination: String, weight: String): Flow<ResponseState<List<ResultCost>>> {
        return remote.getCost(destination, weight)
    }

    override fun addTransaction(transaction: Transaction): Flow<ResponseState<Midtrans>> {
        return remote.addTransaction(transaction)
    }

    override fun getTransaction(): Flow<ResponseState<List<History>>> {
        return remote.getTransaction()
    }

}