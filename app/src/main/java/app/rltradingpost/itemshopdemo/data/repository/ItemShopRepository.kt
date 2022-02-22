package app.rltradingpost.itemshopdemo.data.repository

import app.rltradingpost.itemshopdemo.data.Result
import app.rltradingpost.itemshopdemo.data.local.AppDatabase
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShop
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopItem
import app.rltradingpost.itemshopdemo.data.model.entity.SectionItemJunction
import app.rltradingpost.itemshopdemo.data.model.relation.ItemShopWithSectionsAndItems
import app.rltradingpost.itemshopdemo.data.service.ItemShopApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemShopRepository @Inject constructor(
    private val itemShopApiService: ItemShopApiService,
    db: AppDatabase
) {
    private val itemShopDao = db.itemShopDao()

    suspend fun getItemShop(shopId: String = "2022-02-12") =
        flow<Result<ItemShopWithSectionsAndItems>> {
            itemShopDao.getItemShopWithSectionsAndItemsByShopId(shopId)?.let {
                emit(Result.loading(it))
            }

            val result = itemShopApiService.getItemShop()
            if (result.isSuccessful && result.body() != null) {
                val responseBody = result.body()!!
                for (section in responseBody.sections) {
                    section.itemShopId = responseBody.shopId
                }

                val itemShop = ItemShop(responseBody.shopId, responseBody.message)
                val items = responseBody.items
                val sections = responseBody.sections
                val junctions = buildSectionToItemJunctions(items)

                itemShopDao.insertAll(itemShop, items, sections, junctions)

                itemShopDao.getItemShopWithSectionsAndItemsByShopId(shopId)?.let {
                    emit(Result.success(it))
                } ?: kotlin.run { emit(Result.error("data not retrieved")) }
            } else {
                emit(Result.error(result.errorBody()?.string() ?: "error"))
            }
        }.flowOn(Dispatchers.IO)

    private fun buildSectionToItemJunctions(items: List<ItemShopItem>): List<SectionItemJunction> {
        val junctions = mutableListOf<SectionItemJunction>()
        for (item in items) {
            junctions.add(SectionItemJunction(item.sectionId, item.itemId))
        }
        return junctions.toList()
    }
}