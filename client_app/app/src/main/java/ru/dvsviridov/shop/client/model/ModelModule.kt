package ru.dvsviridov.shop.client.model

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.dvsviridov.shop.client.model.network.ShopService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModelModule {

    @Provides
    @Singleton
    fun provideShopItemsApiRepository(shopService: ShopService): ShopApiRepository {
        return ShopApiRepository(shopService)
    }
}