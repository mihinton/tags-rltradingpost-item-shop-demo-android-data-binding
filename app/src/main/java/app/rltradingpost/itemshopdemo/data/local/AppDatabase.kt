package app.rltradingpost.itemshopdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShop
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopItem
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopSection
import app.rltradingpost.itemshopdemo.data.model.entity.SectionItemJunction

@Database(
    entities = [
        ItemShop::class,
        ItemShopSection::class,
        ItemShopItem::class,
        SectionItemJunction::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemShopDao(): ItemShopDao
}