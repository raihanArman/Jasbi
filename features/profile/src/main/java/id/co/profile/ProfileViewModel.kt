package id.co.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.User
import id.co.core.data.response.ResponseState
import id.co.core.domain.usecase.Usecase

class ProfileViewModel(
    val usecase: Usecase
): ViewModel() {

    fun getUser(): LiveData<ResponseState<List<User>>>{
        return usecase.getUser().asLiveData()
    }

    fun editUser(user: User): LiveData<ResponseState<String>>{
        return usecase.editUser(user).asLiveData()
    }

}