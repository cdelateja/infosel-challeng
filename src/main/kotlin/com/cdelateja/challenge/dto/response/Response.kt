package com.cdelateja.challenge.dto.response

import com.cdelateja.challenge.util.exception.ServiceException
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import org.springframework.http.HttpStatus

class Response {

    companion object {
        fun error(message: String?): Response {
            return Response(message)
        }
    }

    var responseStatus: Int? = null
    var responseError: String? = null
    var result: Any? = null

    constructor() {}

    constructor(e: ServiceException) {
        setException(e)
    }

    constructor(responseStatus: Int, result: Any?) {
        this.responseStatus = responseStatus
        this.result = result
        this.responseError = "OK"
    }

    constructor(message: String?) {
        this.responseStatus = HttpStatus.INTERNAL_SERVER_ERROR.value()
        this.result = null
        this.responseError = message
    }


    fun setException(e: ServiceException) {
        responseStatus = e.status
        responseError = e.message
    }

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
