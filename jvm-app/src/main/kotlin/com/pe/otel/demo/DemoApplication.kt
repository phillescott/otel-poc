package com.pe.otel.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class DemoApplication {

	@GetMapping
	fun get(@RequestParam("name") name: String) {

	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
