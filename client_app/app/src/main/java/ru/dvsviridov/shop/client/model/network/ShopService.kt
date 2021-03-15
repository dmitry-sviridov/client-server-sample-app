package ru.dvsviridov.shop.client.model.network

import retrofit2.Response
import retrofit2.http.GET
import ru.dvsviridov.shop.client.model.dto.Item

interface ShopService {

    @GET("api/v1/shop/items")
    suspend fun fetchItems(): Response<List<Item>>
}