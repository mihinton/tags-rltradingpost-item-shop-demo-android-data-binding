package app.rltradingpost.itemshopdemo.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ItemShop(
    @PrimaryKey
    val key: String,
    val featuredStartDate: Date,
    val featuredEndDate: Date,
    val dailyStartDate: Date,
    val dailyEndDate: Date,
    val message: String? = null
) {
    @Ignore
    var featuredItems: List<RocketLeagueItem> = listOf()
    @Ignore
    var dailyItems: List<RocketLeagueItem> = listOf()
}
