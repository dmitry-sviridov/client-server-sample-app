package ru.dvsviridov.shop.client.model

import retrofit2.Response
import ru.dvsviridov.shop.client.model.dto.Item
import ru.dvsviridov.shop.client.model.network.ShopService
import javax.inject.Inject

class ShopApiRepository
@Inject constructor(private val shopService: ShopService) {

    suspend fun getItemsFromServer(): Response<List<Item>> {
        return shopService.fetchItems()
    }
}