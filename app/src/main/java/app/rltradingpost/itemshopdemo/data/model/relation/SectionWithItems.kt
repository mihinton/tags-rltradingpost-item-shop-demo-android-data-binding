package app.rltradingpost.itemshopdemo.data.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopItem
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopSection
import app.rltradingpost.itemshopdemo.data.model.entity.SectionItemJunction

data class SectionWithItems(
    @Embedded val section: ItemShopSection,
    @Relation(
        parentColumn = "sectionId",
        entityColumn = "itemId",
        associateBy = Junction(SectionItemJunction::class)
    )
    val items: List<ItemShopItem>
)
