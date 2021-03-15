package ru.dvsviridov.shop.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoShopAppApplication

fun main(args: Array<String>) {
	runApplication<DemoShopAppApplication>(*args)
}
