package ru.dvsviridov.shop.backend.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.dvsviridov.shop.backend.data.ShopItemEntity
import ru.dvsviridov.shop.backend.data.ShopItemRepository
import ru.dvsviridov.shop.backend.exception.ShopItemNotFoundException
import java.lang.RuntimeException

import java.util.*

@Service
class ShopService @Autowired constructor(val shopItemRepository: ShopItemRepository) {

    val logger: Logger = LoggerFactory.getLogger(ShopService::class.java)

    fun getAllItems(): List<ShopItemEntity> {
        logger.debug("Отправляю запрос в базу данных на поиск всех товаров")
        return shopItemRepository.findAll()
    }

    fun getItemById(id: Long): ShopItemEntity {
        logger.debug("Отправляю запрос в базу данных на поиск товара с id = ${id}")
        return shopItemRepository.findById(id).orElseThrow {
            logger.debug("Товар с id = ${id} не найден в базе, генерирую ошибку 404 not found")
            ShopItemNotFoundException(id)
        }
    }

    fun saveNewItem(item: ShopItemEntity): ShopItemEntity {
        logger.debug("Отправляю запрос в базу данных на сохранение товара title=${item.title} description= ${item.description}")
        return shopItemRepository.save(item)
    }

    fun deleteItem(id: Long) {
        logger.debug("Отправляю запрос в базу данных на сохранение товара id=${id}")
        return shopItemRepository.deleteById(id)
    }
}