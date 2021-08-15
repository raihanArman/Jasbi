package id.co.core.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.co.core.data.model.Menu

class DatabaseConverter {
    var gson = Gson()

    @TypeConverter
    fun productToString(menu: Menu): String{
        return gson.toJson(menu)
    }

    @TypeConverter
    fun stringToMenu(data: String): Menu{
        val listType = object : TypeToken<Menu>(){}.type
        return gson.fromJson(data, listType)
    }

}