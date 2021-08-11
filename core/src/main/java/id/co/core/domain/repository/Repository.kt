package id.co.core.domain.repository

import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.response.ResponseState
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getProduct(): Flow<ResponseState<List<Menu>>>
    fun loginUser(email: String, password: String): Flow<ResponseState<User>>
    fun registerUser(
        name: String,
        email: String,
        phoneNumber: String,
        address: String,
        password: String
    ): Flow<ResponseState<String>>
}