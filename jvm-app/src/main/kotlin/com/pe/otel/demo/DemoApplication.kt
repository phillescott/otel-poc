package com.pe.otel.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class DemoApplication {

	val logger: Logger = LoggerFactory.getLogger(DemoApplication::class.java)

	@GetMapping("/whats-my-name")
	fun get(@RequestParam("name") name: String): String {
		logger.info("name is {}", name) // !interesting: or is it, just a normal log line...
		return "name is $name"
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
