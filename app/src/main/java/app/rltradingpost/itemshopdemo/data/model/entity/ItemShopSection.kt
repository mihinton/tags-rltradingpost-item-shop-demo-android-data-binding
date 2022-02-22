package app.rltradingpost.itemshopdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemShopSection(
    @PrimaryKey
    val sectionId: String,
    val name: String,
    val endTimestamp: Long,
    val order: Int
) {
    lateinit var itemShopId: String
}