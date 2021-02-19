package com.cdelateja.challenge.controller

import com.cdelateja.challenge.dto.request.PageRequest
import com.cdelateja.challenge.dto.response.Response
import com.cdelateja.challenge.service.InstaPageService
import com.cdelateja.challenge.util.exception.ServiceException
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.apache.logging.log4j.LogManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping(value = ["/api/v1"])
class PageController(private val instaPageService: InstaPageService) {

    companion object {
        private val log = LogManager.getLogger(PageController::class)
    }

    @ApiOperation(value = "Obtener paginas",
            notes = "Obtener paginas",
            produces = "application/json",
            response = Response::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Ok"),
        ApiResponse(code = 401, message = "No autorizado"),
        ApiResponse(code = 500, message = "Error interno"),
        ApiResponse(code = 204, message = "Sin registro")])
    @GetMapping(value = ["/landing-pages"], produces = ["application/json"])
    fun obtenerPaginas(): ResponseEntity<*>? {
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(), instaPageService.obtenerPaginas()), HttpStatus.OK)
        } catch (e: ServiceException) {
            log.error("Error en obtenerPaginas: $e")
            ResponseEntity(Response(e), HttpStatus.OK)
        }
    }

    @ApiOperation(value = "Obtener paginas",
            notes = "Obtener paginas",
            produces = "application/json",
            response = Response::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Ok"),
        ApiResponse(code = 401, message = "No autorizado"),
        ApiResponse(code = 500, message = "Error interno"),
        ApiResponse(code = 204, message = "Sin registro")])
    @GetMapping(value = ["/landing-page"], produces = ["application/json"])
    fun obtenerPagina(@RequestParam id: Int): ResponseEntity<*>? {
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(), instaPageService.obtenerPagina(id)), HttpStatus.OK)
        } catch (e: ServiceException) {
            log.error("Error en obtenerPagina: $e")
            ResponseEntity(Response(e), HttpStatus.OK)
        }
    }

    @ApiOperation(value = "Obtener templates",
            notes = "Obtener templates",
            produces = "application/json",
            response = Response::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Ok"),
        ApiResponse(code = 401, message = "No autorizado"),
        ApiResponse(code = 500, message = "Error interno"),
        ApiResponse(code = 204, message = "Sin registro")])
    @GetMapping(value = ["/templates"], produces = ["application/json"])
    fun obtenerTemplates(): ResponseEntity<*>? {
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(), instaPageService.obtenerTemplates()), HttpStatus.OK)
        } catch (e: ServiceException) {
            log.error("Error en obtenerTemplates: $e")
            ResponseEntity(Response(e), HttpStatus.OK)
        }
    }

    @ApiOperation(value = "Guarda nueva pagina",
            notes = "Servicio para Guarda nueva pagina",
            consumes = "application/json",
            produces = "application/json",
            response = Response::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Ok"),
        ApiResponse(code = 401, message = "No autorizado"),
        ApiResponse(code = 500, message = "Error interno"),
        ApiResponse(code = 204, message = "Registro no encontrado")])
    @PostMapping(value = ["/landing-pages"], consumes = ["application/json"], produces = ["application/json"])
    fun guardarPagina(@RequestBody request: PageRequest): ResponseEntity<*>? {
        return try {
            ResponseEntity(Response(HttpStatus.OK.value(),
                    instaPageService.guardarPagina(request)), HttpStatus.OK)
        } catch (e: ServiceException) {
            log.error("Error en guardarPagina: $e")
            ResponseEntity(Response(e), HttpStatus.OK)
        }
    }
}
