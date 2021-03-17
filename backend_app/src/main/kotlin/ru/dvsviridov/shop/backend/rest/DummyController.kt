package ru.dvsviridov.shop.backend.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class DummyController {

    @GetMapping("/zhopa")
    fun getDummyRequest(): String {
        return "zhopa.html"
    }
}