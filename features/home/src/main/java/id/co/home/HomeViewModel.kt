package id.co.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.Menu
import id.co.core.data.response.ResponseState
import id.co.core.domain.usecase.Usecase

class HomeViewModel(
    val usecase: Usecase
): ViewModel() {
    fun getProduct(): LiveData<ResponseState<List<Menu>>> {
        return usecase.getProduct().asLiveData()
    }
}