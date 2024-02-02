package com.example.newtodo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NewTodoApplication

fun main(args: Array<String>) {
    runApplication<NewTodoApplication>(*args)
}
