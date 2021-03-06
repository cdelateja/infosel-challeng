package com.cdelateja.challenge.service

import com.cdelateja.challenge.dto.request.PageRequest
import com.cdelateja.challenge.dto.response.PageResponse
import com.cdelateja.challenge.dto.response.PageSaveResponse
import com.cdelateja.challenge.dto.response.TemplateResponse
import com.cdelateja.challenge.service.client.Client
import com.cdelateja.challenge.util.exception.ServiceException
import com.cdelateja.challenge.util.rest.Result
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class InstaPageService(private val client: Client,
                       private val env: Environment) {

    companion object {
        private val log = LogManager.getLogger(InstaPageService::class)
    }

    @Value("\${instapage.url}")
    private val urlInstaPage: String? = null

    /**
     * Funcion encargada de obtener todas las paginas
     */
    @Throws(ServiceException::class)
    fun obtenerPaginas(): PageResponse {
        return Result.result(client.get(urlInstaPage + env.getProperty("instapage.getPages")))
                .toObject(PageResponse::class.java)
    }

    /**
     * Funcion encargada de obtener el HTML por id
     */
    @Throws(ServiceException::class)
    fun obtenerPagina(id: Int): String {
        return client.get(urlInstaPage +
                env.getProperty("instapage.getPage") + id + "/null/A/1?autoplayDisabled=1")
    }

    /**
     * Funcion encargada de borrar (Cambia estatus) de una pagina
     */
    @Throws(ServiceException::class)
    fun borrarPagina(id: Int): String {
        return client.delete(urlInstaPage + env.getProperty("instapage.deletePage") + id)
    }

    /**
     * Funcion encargada de obtener el catalogo de plantillas
     */
    @Throws(ServiceException::class)
    fun obtenerTemplates(): TemplateResponse {
        return Result.result(client.get(urlInstaPage + env.getProperty("instapage.getTemplates")))
                .toObject(TemplateResponse::class.java)
    }

    /**
     * Funcion encargada de guardar una pagina
     */
    @Throws(ServiceException::class)
    fun guardarPagina(pageRequest: PageRequest): PageSaveResponse {
        return Result.result(client.post(urlInstaPage + env.getProperty("instapage.createPage"), pageRequest))
                .toObject(PageSaveResponse::class.java)
    }
}
