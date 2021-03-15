package ru.dvsviridov.shop.backend.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopItemRepository: JpaRepository<ShopItemEntity, Long>