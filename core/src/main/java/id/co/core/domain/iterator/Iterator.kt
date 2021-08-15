package id.co.core.domain.iterator

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
import id.co.core.domain.repository.Repository
import id.co.core.domain.usecase.Usecase
import kotlinx.coroutines.flow.Flow

class Iterator(
    private val repository: Repository
): Usecase {
    override fun getAllProduct(): Flow<ResponseState<List<CategoryMenu>>> {
        return repository.getAllProduct()
    }

    override fun getProduct(categoryId: String): Flow<ResponseState<List<CategoryMenu>>> {
        return repository.getProduct(categoryId)
    }

    override fun getCategory(): Flow<ResponseState<List<Category>>> {
        return repository.getCategory()
    }

    override fun loginUser(email: String, password: String): Flow<ResponseState<User>> {
        return repository.loginUser(email, password)
    }

    override fun registerUser(
        name: String,
        email: String,
        phoneNumber: String,
        address: String,
        password: String
    ): Flow<ResponseState<String>> {
        return repository.registerUser(name, email, phoneNumber, address, password)
    }

    override fun getUser(): Flow<ResponseState<List<User>>> {
        return repository.getUser()
    }

    override fun editUser(user: User): Flow<ResponseState<String>> {
        return repository.editUser(user)
    }

    override fun insertCart(product: ProductEntitiy): Long {
        return repository.insertCart(product)
    }

    override fun updateQtyCart(qty: Int, idCart: Int) {
        return repository.updateQtyCart(qty, idCart)
    }

    override fun getProductLocal(): List<ProductEntitiy> {
        return repository.getProductLocal()
    }

    override fun getProductById(id: String): ProductEntitiy {
        return repository.getProductById(id)
    }

    override fun cartProductExist(id: String): Boolean {
        return repository.cartProductExist(id)
    }

    override fun deleteAllCart() {
        return repository.deleteAllCart()
    }

    override fun deleteItemCart(product: ProductEntitiy) {
        return repository.deleteItemCart(product)
    }

    override fun getProvince(): Flow<ResponseState<List<Result>>> {
        return repository.getProvince()
    }

    override fun getCity(provinceId: String): Flow<ResponseState<List<Result>>> {
        return repository.getCity(provinceId)
    }

    override fun getCost(destination: String, weight: String): Flow<ResponseState<List<ResultCost>>> {
        return repository.getCost(destination, weight)
    }

    override fun addTransaction(transaction: Transaction): Flow<ResponseState<Midtrans>> {
        return repository.addTransaction(transaction)
    }

    override fun getTransaction(): Flow<ResponseState<List<History>>> {
        return repository.getTransaction()
    }
}