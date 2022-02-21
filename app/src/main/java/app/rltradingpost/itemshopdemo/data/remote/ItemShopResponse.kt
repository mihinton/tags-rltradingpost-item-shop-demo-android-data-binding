package app.rltradingpost.itemshopdemo.data.remote

data class ItemShopResponse(
    val key: String,
    val featuredItems: List<RocketLeagueItemResponse>,
    val featuredStartTimestamp: Long,
    val featuredEndTimestamp: Long,
    val dailyItems: List<RocketLeagueItemResponse>,
    val dailyStartTimestamp: Long,
    val dailyEndTimestamp: Long,
    val message: String? = null,
) {

    data class RocketLeagueItemResponse(
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
    )
}