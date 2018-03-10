package it.stacja.bank.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("sayHello")
    fun sayHello(@RequestParam(value = "name", required = false, defaultValue = "Kotlin") name: String) = "Hello $name"

}