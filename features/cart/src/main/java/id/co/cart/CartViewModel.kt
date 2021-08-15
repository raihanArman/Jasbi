package id.co.cart

import androidx.lifecycle.ViewModel
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.domain.usecase.Usecase

class CartViewModel(
    val usecase: Usecase
): ViewModel() {

    fun getProductLocal(): List<ProductEntitiy>{
        return usecase.getProductLocal()
    }

    fun updateQtyCart(qty: Int, id: Int){
        return usecase.updateQtyCart(qty, id)
    }

    fun deleteItemCart(productEntitiy: ProductEntitiy){
        return usecase.deleteItemCart(productEntitiy)
    }

    fun deleteAllCart(){
        return usecase.deleteAllCart()
    }

}