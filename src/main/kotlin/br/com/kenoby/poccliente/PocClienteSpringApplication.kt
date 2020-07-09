package br.com.kenoby.poccliente

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class PocClienteSpringApplication : SpringBootServletInitializer()

fun main(args: Array<String>) {
	runApplication<PocClienteSpringApplication>(*args)
}
