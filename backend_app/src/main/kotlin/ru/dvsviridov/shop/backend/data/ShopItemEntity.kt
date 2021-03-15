package ru.dvsviridov.shop.backend.data

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "t_shop_items")
data class ShopItemEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @JsonProperty(value = "title")
    @Column(name = "title")
    val title: String,

    @JsonProperty(value = "description")
    @Column(name = "descr")
    val description: String,

    @JsonProperty(value = "is_on_sale")
    @Column(name = "is_on_sale")
    val isOnSale: Boolean?,

    @JsonProperty(value = "price")
    @Column(name = "price")
    val price: Double,

    @JsonProperty(value = "image_url")
    @Column(name = "image_url")
    val imageUrl: String
)
