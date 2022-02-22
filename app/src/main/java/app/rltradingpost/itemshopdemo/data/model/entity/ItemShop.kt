package app.rltradingpost.itemshopdemo.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemShop(
    @PrimaryKey
    val shopId: String,
    val message: String? = null
)