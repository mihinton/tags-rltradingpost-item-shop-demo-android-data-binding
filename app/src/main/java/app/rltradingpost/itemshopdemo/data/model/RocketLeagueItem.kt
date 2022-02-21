package app.rltradingpost.itemshopdemo.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["itemShopId", "rlId"])
data class RocketLeagueItem(
    val itemShopId: String,
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
    val isFeatured: Boolean
)
