package id.co.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.User
import id.co.core.data.response.ResponseState
import id.co.core.domain.usecase.Usecase

class LoginViewModel(
    val useCase: Usecase
): ViewModel() {
    fun loginUser(email: String, password: String): LiveData<ResponseState<User>> {
        return useCase.loginUser(email, password).asLiveData()
    }

    fun registerUser(
        name: String,
        email: String,
        phoneNumber: String,
        address: String,
        password: String
    ): LiveData<ResponseState<String>> {
        return useCase.registerUser(name, email, phoneNumber, address, password).asLiveData()
    }
}