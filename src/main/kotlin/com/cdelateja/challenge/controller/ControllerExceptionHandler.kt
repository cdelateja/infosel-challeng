package com.cdelateja.challenge.controller

import com.cdelateja.challenge.dto.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun exceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<*>? {
        println(e.message)
        return ResponseEntity(Response.error(e.message),
                HttpStatus.OK)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun exceptionHandler(e: HttpMessageNotReadableException): ResponseEntity<*>? {
        return ResponseEntity(Response.error(e.message),
                HttpStatus.OK)
    }

    private fun prettyMessage(e: MethodArgumentNotValidException): String {
        val message = StringBuilder()
        e.bindingResult.allErrors.forEach {
            message.append(it.objectName).append("\n")
        }
        return message.toString()
    }

}
