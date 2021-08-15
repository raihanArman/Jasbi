package id.co.core.data.db.dao

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.data.model.Menu
import retrofit2.http.DELETE

@Dao
interface DatabaseDao {
    @Insert
    fun insertCart(produk: ProductEntitiy): Long

    @Query("UPDATE tb_product SET qty = :qty WHERE id = :idCart")
    fun updateCartById(qty: Int, idCart: Int)

    @Query("SELECT * FROM tb_product")
    fun getProductLocal(): List<ProductEntitiy>

    @Query("SELECT * FROM tb_product WHERE idMenu = :id")
    fun getProductById(id: String): ProductEntitiy

    @Query("SELECT EXISTS (SELECT * FROM tb_product WHERE idMenu = :id)")
    fun cartProductExist(id: String): Boolean

    @Delete
    fun deleteItemCart(produk: ProductEntitiy)

    @Query("DELETE FROM tb_product")
    fun deleteAllCart()
}