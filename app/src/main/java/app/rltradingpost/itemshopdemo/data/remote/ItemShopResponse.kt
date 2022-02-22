package app.rltradingpost.itemshopdemo.data.remote

import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopItem
import app.rltradingpost.itemshopdemo.data.model.entity.ItemShopSection

data class ItemShopResponse(
    val shopId: String,
    val items: List<ItemShopItem>,
    val sections: List<ItemShopSection>,
    val message: String? = null,
)