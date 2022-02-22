package app.rltradingpost.itemshopdemo.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShop
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopSection

data class ItemShopWithSectionsAndItems(
    @Embedded val itemShop: ItemShop,
    @Relation(
        entity = ItemShopSection::class,
        parentColumn = "shopId",
        entityColumn = "itemShopId"
    )
    val sectionsWithItems: List<SectionWithItems>
)
