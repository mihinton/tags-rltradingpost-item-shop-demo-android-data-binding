package app.rltradingpost.itemshopdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemShopItem(
    @PrimaryKey
    val itemId: String,
    val rlId: Int,
    val name: String,
    val category: String,
    val color: String,
    val certification: String,
    val rarity: String,
    val price: Int,
    val tradable: Boolean,
    val imageUrl: String,
    val previewUrl: String? = null,
    val sectionId: String,
    val order: Int
)