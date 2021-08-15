package id.co.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Category
import id.co.core.data.model.CategoryMenu
import id.co.core.data.model.Menu
import id.co.core.data.model.User
import id.co.core.data.response.ResponseState
import id.co.core.domain.usecase.Usecase

class HomeViewModel(
    val usecase: Usecase
): ViewModel() {

    companion object{
        private const val TAG = "HomeViewModel"
    }

    fun getAllProduct(): LiveData<ResponseState<List<CategoryMenu>>> {
        return usecase.getAllProduct().asLiveData()
    }

    fun getProduct(categoryId: String): LiveData<ResponseState<List<CategoryMenu>>> {
        return usecase.getProduct(categoryId).asLiveData()
    }

    fun getCategory(): LiveData<ResponseState<List<Category>>> {
        return usecase.getCategory().asLiveData()
    }

    fun insertProduct(menu: Menu){
        val checkExists = usecase.cartProductExist(menu.idProduk!!)
        if(checkExists){
            val productCart = usecase.getProductById(menu.idProduk!!)
            val qtyCurrent = productCart.qty.plus(1)
            usecase.updateQtyCart(qtyCurrent, productCart.id)
            Log.d(TAG, "updateProduct: Berhasil")
        }else{
            val productEntitiy = ProductEntitiy(
                0,
                menu.idProduk!!,
                menu,
                1
            )
            if(usecase.insertCart(productEntitiy) > 0){
                Log.d(TAG, "insertProduct: Berhasil")
            }else{
                Log.d(TAG, "insertProduct: Gagal")
            }
        }

    }

    fun getProductLocal(): List<ProductEntitiy>{
        return usecase.getProductLocal()
    }

    fun getUser(): LiveData<ResponseState<List<User>>>{
        return usecase.getUser().asLiveData()
    }

}