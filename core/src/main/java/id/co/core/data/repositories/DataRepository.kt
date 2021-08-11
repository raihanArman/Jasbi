package id.co.core.data.repositories

import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.repositories.remote.RemoteDataSource
import id.co.core.data.response.ResponseState
import id.co.core.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remote: RemoteDataSource
): Repository{
    override fun getProduct(): Flow<ResponseState<List<Menu>>> {
        return remote.getProduct()
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

}