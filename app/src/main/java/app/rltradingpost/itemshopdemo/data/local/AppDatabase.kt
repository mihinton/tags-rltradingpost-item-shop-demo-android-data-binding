package app.rltradingpost.itemshopdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.rltradingpost.itemshopdemo.data.model.ItemShop
import app.rltradingpost.itemshopdemo.data.model.RocketLeagueItem

@Database(
    entities = [
        ItemShop::class,
        RocketLeagueItem::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemShopDao(): ItemShopDao
    abstract fun rocketLeagueItemDao(): RocketLeagueItemDao
}