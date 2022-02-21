package app.rltradingpost.itemshopdemo.hilt

import android.content.Context
import androidx.room.Room
import app.rltradingpost.itemshopdemo.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "rltp-item-shop-demo-db"
        ).fallbackToDestructiveMigration().build()
    }
}