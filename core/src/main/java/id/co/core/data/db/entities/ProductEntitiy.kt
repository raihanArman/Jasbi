package id.co.core.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.co.core.data.model.Menu

@Entity(tableName = "tb_product")
data class ProductEntitiy(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("id_produk")
    var idMenu: String,
    var product: Menu,
    @SerializedName("jumlah")
    var qty: Int
)