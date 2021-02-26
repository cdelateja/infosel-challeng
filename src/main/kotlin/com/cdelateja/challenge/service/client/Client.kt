package com.cdelateja.challenge.service.client

import com.cdelateja.challenge.util.exception.ServiceException
import com.cdelateja.challenge.util.json.JsonUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.util.*

@Service
class Client {

    companion object {
        private val log = LoggerFactory.getLogger(Client::class.java)
    }

    enum class RequestType {
        GET, POST, PUT, DELETE
    }

    @Value("\${instapage.token}")
    private val token: String? = null

    private val restTemplate: RestTemplate = RestTemplate()

    @Throws(ServiceException::class)
    fun post(url: String, jsonBody: Any): String {
        return request(RequestType.POST, url, JsonUtil.getJsonString(jsonBody))
    }

    @Throws(ServiceException::class)
    fun get(url: String): String {
        return request(RequestType.GET, url, null)
    }

    @Throws(ServiceException::class)
    fun delete(url: String): String {
        return request(RequestType.DELETE, url, null)
    }

    @Throws(ServiceException::class)
    fun request(requestType: RequestType, url: String, jsonBody: String?): String {
        return getRequest(typeOfResponse(requestType, url, jsonBody))
    }

    @Throws(ServiceException::class)
    fun getRequest(response: ResponseEntity<String>): String {
        val jsonOutput = response.body
        validateResponse(response)
        return try {
            log.info("--------------> Output : $jsonOutput")
            if (Objects.isNull(jsonOutput)) {
                return ""
            }
            jsonOutput!!
        } catch (e: IOException) {
            throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message)
        }
    }

    @Throws(ServiceException::class)
    fun validateResponse(response: ResponseEntity<String>) {
        val status = response.statusCode.value()
        log.info("--------------> Status : $status")
        when (status) {
            HttpStatus.BAD_REQUEST.value() ->
                throw ServiceException(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.reasonPhrase)
            HttpStatus.UNAUTHORIZED.value() ->
                throw ServiceException(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.reasonPhrase)
            HttpStatus.INTERNAL_SERVER_ERROR.value() ->
                throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
        }
    }

    @Throws(ServiceException::class)
    fun typeOfResponse(requestType: RequestType, url: String, jsonBody: String?): ResponseEntity<String> {
        val headers = getHeaders()
        val entity = HttpEntity(jsonBody, headers)
        try { // Complete URL
            log.info("--------------> Petition[$requestType]: $url")
            log.info("--------------> JsonSend: $jsonBody")
            return when (requestType) {
                RequestType.GET -> restTemplate.exchange(url, HttpMethod.GET, entity, String::class.java)
                RequestType.POST -> restTemplate.exchange(url, HttpMethod.POST, entity, String::class.java)
                RequestType.PUT -> restTemplate.exchange(url, HttpMethod.PUT, entity, String::class.java)
                RequestType.DELETE -> restTemplate.exchange(url, HttpMethod.DELETE, entity, String::class.java)
            }
        } catch (ex: RestClientException) {
            log.error("Client exception ----------> " + ex.message)
            throw ServiceException(HttpStatus.SERVICE_UNAVAILABLE.value(), "Error on Client[typeOfResponse]: ${ex.message}")
        }
    }

    fun getHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_JSON)
        headers.contentType = MediaType.APPLICATION_JSON
        headers.setBearerAuth(token!!)
        return headers
    }

}
