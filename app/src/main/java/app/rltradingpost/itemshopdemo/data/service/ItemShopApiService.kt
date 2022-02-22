package app.rltradingpost.itemshopdemo.data.service

import app.rltradingpost.itemshopdemo.data.remote.ItemShopResponse
import retrofit2.Response
import retrofit2.http.GET

interface ItemShopApiService {
    @GET("/itemShop-getAndroid")
    suspend fun getItemShop(): Response<ItemShopResponse>
}