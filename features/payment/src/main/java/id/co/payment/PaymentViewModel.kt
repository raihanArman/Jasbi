package id.co.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.cost.ResultCost
import id.co.core.data.model.province.Result
import id.co.core.data.model.shipment.Midtrans
import id.co.core.data.model.shipment.Transaction
import id.co.core.data.response.ResponseState
import id.co.core.domain.usecase.Usecase

class PaymentViewModel(
    val usecase: Usecase
): ViewModel() {

    fun getProvince(): LiveData<ResponseState<List<Result>>>{
        return usecase.getProvince().asLiveData()
    }

    fun getCity(provinceId: String): LiveData<ResponseState<List<Result>>>{
        return usecase.getCity(provinceId).asLiveData()
    }

    fun getCost(destination: String, weight: String): LiveData<ResponseState<List<ResultCost>>>{
        return usecase.getCost(destination, weight).asLiveData()
    }

    fun getProductLocal(): List<ProductEntitiy>{
        return usecase.getProductLocal()
    }

    fun updateQtyCart(qty: Int, id: Int){
        return usecase.updateQtyCart(qty, id)
    }

    fun addTransaction(transaction: Transaction): LiveData<ResponseState<Midtrans>>{
        return usecase.addTransaction(transaction).asLiveData()
    }

    fun deleteItemCart(productEntitiy: ProductEntitiy){
        return usecase.deleteItemCart(productEntitiy)
    }

    fun deleteAllCart(){
        return usecase.deleteAllCart()
    }

}