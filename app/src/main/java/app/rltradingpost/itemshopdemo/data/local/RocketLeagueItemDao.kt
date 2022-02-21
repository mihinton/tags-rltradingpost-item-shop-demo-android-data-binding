package app.rltradingpost.itemshopdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.rltradingpost.itemshopdemo.data.model.RocketLeagueItem

@Dao
interface RocketLeagueItemDao {
    @Query("SELECT * FROM RocketLeagueItem WHERE itemShopId = :itemShopId AND isFeatured = :isFeatured")
    fun findByShopId(itemShopId: String, isFeatured: Boolean): List<RocketLeagueItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rocketLeagueItem: List<RocketLeagueItem>)
}