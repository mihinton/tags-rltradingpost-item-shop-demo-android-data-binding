package app.rltradingpost.itemshopdemo.api

import app.rltradingpost.itemshopdemo.data.remote.ItemShopResponse
import retrofit2.Response
import retrofit2.http.GET

interface ItemShopApiService {
    @GET("/itemShop-get")
    suspend fun getItemShop(): Response<ItemShopResponse>
}