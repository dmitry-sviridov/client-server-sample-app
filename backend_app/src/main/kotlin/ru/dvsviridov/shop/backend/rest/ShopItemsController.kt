package ru.dvsviridov.shop.backend.rest

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.dvsviridov.shop.backend.data.ShopItemEntity
import ru.dvsviridov.shop.backend.service.ShopService

@RestController
@RequestMapping("/api/v1/shop")
class ShopItemsController @Autowired constructor(val shopService: ShopService) {

    val logger = LoggerFactory.getLogger(ShopItemsController::class.java)

    @GetMapping("/items")
    fun getAllItems(): List<ShopItemEntity> {
        logger.debug("Получен запрос на список товаров")
        return shopService.getAllItems()
    }

    @PostMapping("/items/")
    fun postNewItem(@RequestBody item: ShopItemEntity): ShopItemEntity {
        logger.debug("Получен запрос на добавление товара")
        return shopService.saveNewItem(item)
    }

    @GetMapping("/items/{id}")
    fun getItemWithId(@PathVariable("id") id: Long): ShopItemEntity {
        logger.debug("Получен запрос на получение единичного товара по id = $id")
        return shopService.getItemById(id)
    }

    @DeleteMapping("/items/{id}")
    fun deleteItemWithId(@PathVariable("id") id: Long) {
        logger.debug("Получен запрос на удаление единичного товара по id = $id")
        return shopService.deleteItem(id)
    }
}