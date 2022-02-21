package app.rltradingpost.itemshopdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.rltradingpost.itemshopdemo.data.model.ItemShop

@Dao
interface ItemShopDao {
    @Query("SELECT * FROM ItemShop WHERE `key` = :key")
    fun findById(key: String): ItemShop?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemShop: ItemShop)
}