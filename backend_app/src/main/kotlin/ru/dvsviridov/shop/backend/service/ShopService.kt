package ru.dvsviridov.shop.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.dvsviridov.shop.backend.data.ShopItemEntity
import ru.dvsviridov.shop.backend.data.ShopItemRepository
import ru.dvsviridov.shop.backend.exception.ShopItemNotFoundException
import java.lang.RuntimeException

import java.util.*

@Service
class ShopService @Autowired constructor(val shopItemRepository: ShopItemRepository) {

    fun getAllItems(): List<ShopItemEntity> {
        return shopItemRepository.findAll()
    }

    fun getItemById(id: Long): ShopItemEntity {
        return shopItemRepository.findById(id).orElseThrow {
            ShopItemNotFoundException(id)
        }
    }

}