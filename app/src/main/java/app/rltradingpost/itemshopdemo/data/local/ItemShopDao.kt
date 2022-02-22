package app.rltradingpost.itemshopdemo.data.local

import androidx.room.*
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShop
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopItem
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopSection
import app.rltradingpost.itemshopdemo.data.model.entity.SectionItemJunction
import app.rltradingpost.itemshopdemo.data.model.relation.ItemShopWithSectionsAndItems

@Dao
interface ItemShopDao {
    @Transaction
    @Query("SELECT * FROM ItemShop WHERE shopId = :shopId")
    fun getItemShopWithSectionsAndItemsByShopId(shopId: String): ItemShopWithSectionsAndItems?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShop(itemShop: ItemShop)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items: List<ItemShopItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSections(sections: List<ItemShopSection>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJunctions(junctions: List<SectionItemJunction>)

    @Transaction
    fun insertAll(
        itemShop: ItemShop,
        items: List<ItemShopItem>,
        sections: List<ItemShopSection>,
        junctions: List<SectionItemJunction>
    ) {
        insertShop(itemShop)
        insertItems(items)
        insertSections(sections)
        insertJunctions(junctions)
    }
}