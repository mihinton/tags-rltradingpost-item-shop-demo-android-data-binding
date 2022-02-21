package app.rltradingpost.itemshopdemo.hilt

import app.rltradingpost.itemshopdemo.api.ItemShopApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideItemShopApiService(retrofit: Retrofit): ItemShopApiService {
        return retrofit.create()
    }
}