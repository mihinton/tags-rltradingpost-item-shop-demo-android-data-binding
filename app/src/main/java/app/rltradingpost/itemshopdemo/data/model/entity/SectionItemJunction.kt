package app.rltradingpost.itemshopdemo.data.model.entity

import androidx.room.Entity

@Entity(primaryKeys = ["sectionId", "itemId"])
data class SectionItemJunction(
    val sectionId: String,
    val itemId: String
)
