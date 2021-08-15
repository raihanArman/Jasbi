package id.co.jasbi

import androidx.lifecycle.ViewModel
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.domain.usecase.Usecase

class HomeViewModel(
    val usecase: Usecase
): ViewModel() {

    fun getProductLocal(): List<ProductEntitiy>{
        return usecase.getProductLocal()
    }

}