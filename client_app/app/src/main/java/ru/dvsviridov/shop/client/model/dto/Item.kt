package ru.dvsviridov.shop.client.model.dto

import com.squareup.moshi.Json

data class Item(
    @Json(name = "id")           val id: Int,
    @Json(name = "title")        val title: String,
    @Json(name = "description")  val description: String?,
    @Json(name = "is_on_sale")   val isOnSale: Boolean?,
    @Json(name = "price")        val price: Double,
    @Json(name = "image_url")    val imageUrl: String,
)