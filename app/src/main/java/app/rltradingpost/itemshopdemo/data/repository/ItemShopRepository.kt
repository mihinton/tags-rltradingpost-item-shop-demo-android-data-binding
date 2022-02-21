package app.rltradingpost.itemshopdemo.data.repository

import app.rltradingpost.itemshopdemo.api.ItemShopApiService
import app.rltradingpost.itemshopdemo.data.Result
import app.rltradingpost.itemshopdemo.data.local.AppDatabase
import app.rltradingpost.itemshopdemo.data.model.ItemShop
import app.rltradingpost.itemshopdemo.data.model.RocketLeagueItem
import app.rltradingpost.itemshopdemo.data.remote.ItemShopResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class ItemShopRepository @Inject constructor(
    private val itemShopApiService: ItemShopApiService,
    db: AppDatabase
) {
    private val itemShopDao = db.itemShopDao()
    private val rocketLeagueItemDao = db.rocketLeagueItemDao()

    suspend fun getItemShop(key: String = "2022-02-12") = flow<Result<ItemShop>> {
        itemShopDao.findById(key)?.let {
            it.featuredItems = rocketLeagueItemDao.findByShopId(key, true)
            it.dailyItems = rocketLeagueItemDao.findByShopId(key, false)
            emit(Result.loading(it))
        } ?: kotlin.run { emit(Result.loading()) }

        val result = itemShopApiService.getItemShop()
        if (result.isSuccessful && result.body() != null) {
            val responseBody = result.body()!!
            val itemShop = buildItemShopEntity(responseBody)
            val featuredItems = buildFeaturedItemsList(responseBody)
            val dailyItems = buildDailyItemsList(responseBody)
            itemShop.featuredItems = featuredItems
            itemShop.dailyItems = dailyItems

            itemShopDao.insert(itemShop)
            rocketLeagueItemDao.insertAll(featuredItems)
            rocketLeagueItemDao.insertAll(dailyItems)

            emit(Result.success(itemShop))
        } else {
            emit(Result.error(result.errorBody()?.string() ?: "error"))
        }
    }.flowOn(Dispatchers.IO)

    private fun buildItemShopEntity(response: ItemShopResponse): ItemShop {
        return ItemShop(
            key = response.key,
            featuredStartDate = Date(response.featuredStartTimestamp),
            featuredEndDate = Date(response.featuredEndTimestamp),
            dailyStartDate = Date(response.dailyStartTimestamp),
            dailyEndDate = Date(response.dailyEndTimestamp),
            message = response.message
        )
    }

    private fun buildFeaturedItemsList(itemShopResponse: ItemShopResponse): List<RocketLeagueItem> {
        val featuredItemList = mutableListOf<RocketLeagueItem>()
        for (item in itemShopResponse.featuredItems) {
            featuredItemList.add(
                RocketLeagueItem(
                    itemShopId = itemShopResponse.key,
                    rlId = item.rlId,
                    name = item.name,
                    category = item.category,
                    color = item.color,
                    certification = item.certification,
                    rarity = item.rarity,
                    price = item.price,
                    tradable = item.tradable,
                    imageUrl = item.imageUrl,
                    previewUrl = item.previewUrl,
                    isFeatured = true
                )
            )
        }
        return featuredItemList.toList()
    }

    private fun buildDailyItemsList(itemShopResponse: ItemShopResponse): List<RocketLeagueItem> {
        val featuredItemList = mutableListOf<RocketLeagueItem>()
        for (item in itemShopResponse.dailyItems) {
            featuredItemList.add(
                RocketLeagueItem(
                    itemShopId = itemShopResponse.key,
                    rlId = item.rlId,
                    name = item.name,
                    category = item.category,
                    color = item.color,
                    certification = item.certification,
                    rarity = item.rarity,
                    price = item.price,
                    tradable = item.tradable,
                    imageUrl = item.imageUrl,
                    previewUrl = item.previewUrl,
                    isFeatured = false
                )
            )
        }
        return featuredItemList.toList()
    }
}