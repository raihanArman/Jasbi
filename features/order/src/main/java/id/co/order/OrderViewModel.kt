package id.co.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.model.history.History
import id.co.core.data.response.ResponseState
import id.co.core.domain.usecase.Usecase

class OrderViewModel(
    val usecase: Usecase
): ViewModel() {
    fun getTransaction(): LiveData<ResponseState<List<History>>> {
        return usecase.getTransaction().asLiveData()
    }
}