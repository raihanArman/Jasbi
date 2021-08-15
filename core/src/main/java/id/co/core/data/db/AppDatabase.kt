package id.co.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.co.core.data.db.dao.DatabaseDao
import id.co.core.data.db.entities.ProductEntitiy
import id.co.core.util.DatabaseConverter


@Database(
    entities =[
        ProductEntitiy::class
    ], version = 1, exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}