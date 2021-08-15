package id.co.core.data.repositories.local

import id.co.core.data.db.dao.DatabaseDao
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Menu

class LocalDataSource(
    val databaseDao: DatabaseDao
) {
    fun insertCart(product: ProductEntitiy): Long {
        return databaseDao.insertCart(product)
    }

    fun updateQtyCart(qty: Int, idCart: Int){
        return databaseDao.updateCartById(qty, idCart)
    }

    fun getProductLocal(): List<ProductEntitiy>{
        return databaseDao.getProductLocal()
    }

    fun getProductById(id: String): ProductEntitiy{
        return databaseDao.getProductById(id)
    }

    fun cartProductExist(id: String): Boolean{
        return databaseDao.cartProductExist(id)
    }
    fun deleteItemCart(product: ProductEntitiy){
        return databaseDao.deleteItemCart(product)
    }

    fun deleteAllCart(){
        return databaseDao.deleteAllCart()
    }

}