package ru.dvsviridov.shop.backend.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.dvsviridov.shop.backend.data.ShopItemEntity
import ru.dvsviridov.shop.backend.service.ShopService
import java.lang.RuntimeException
import java.util.*

@RestController
@RequestMapping("/api/v1/shop")
class ShopItemsController @Autowired constructor(val shopService: ShopService) {

    @GetMapping("/items")
    fun getAllItems(): List<ShopItemEntity> {
        return shopService.getAllItems()
    }

    @GetMapping("/items/{id}")
    fun getItemWithId(@PathVariable("id") id: Long): ShopItemEntity {
        return shopService.getItemById(id)
    }
}