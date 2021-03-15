package ru.dvsviridov.shop.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

class ShopItemNotFoundException
    constructor(itemId: Long): RuntimeException("Item with id = $itemId not exists")


@ControllerAdvice
class ShopItemNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ShopItemNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun shopItemNotFoundHandler(ex: ShopItemNotFoundException): ErrorBody {
        return ErrorBody(HttpStatus.NOT_FOUND.value(), ex.message)
    }

}

data class ErrorBody(
    val code: Int,
    val reason: String?,
)