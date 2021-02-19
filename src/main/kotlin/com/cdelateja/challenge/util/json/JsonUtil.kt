package com.cdelateja.challenge.util.json

import com.cdelateja.challenge.util.exception.ServiceException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import java.io.IOException

class JsonUtil {
    companion object {
        private val mapper = ObjectMapper()

        /**
         * Transforms any object to Json String
         */
        @Throws(ServiceException::class)
        fun getJsonString(json: Any): String {
            return try {
                mapper.writeValueAsString(json)
            } catch (e: JsonProcessingException) {
                throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error getting json string: " + e.cause?.message)
            }
        }

        @Throws(ServiceException::class)
        fun getJsonNode(jsonString: String): JsonNode {
            return try {
                mapper.readValue(jsonString, JsonNode::class.java)
            } catch (e: JsonProcessingException) {
                throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error getting JsonNode: " + e.cause?.message)
            }
        }

        @Throws(ServiceException::class)
        fun <T> getObjectFromJsonNode(sonBody: JsonNode, clazz: Class<T>?): T {
            return try {
                mapper.treeToValue(sonBody, clazz)
            } catch (e: IOException) {
                throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.message)
            }
        }

        @Throws(ServiceException::class)
        fun getJsonNodeFromAny(dto: Any): JsonNode {
            return try {
                mapper.readValue(getJsonString(dto), JsonNode::class.java)
            } catch (e: JsonProcessingException) {
                throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error getting JsonNode: " + e.cause?.message)
            }
        }

        @Throws(ServiceException::class)
        fun paramsToJson(names: Array<String>, vararg params: Any): String {
            val json = StringBuffer("{")
            json.append(getJson(names, *params))
            json.append("}")
            return json.toString()
        }

        @Throws(ServiceException::class)
        fun getJson(names: Array<String>, vararg params: Any): String {
            val jsonValue = StringBuffer()
            val var5 = names.size
            for ((x, var6) in (0 until var5).withIndex()) {
                val name = names[var6]
                if (params[x] is Collection<*>) {
                    val valor: String = getJsonString(params[x])
                    jsonValue.append("\"$name\":$valor,")
                } else {
                    jsonValue.append("\"" + name + "\":\"" + params[x] + "\",")
                }
            }
            if (jsonValue.isNotEmpty()) {
                jsonValue.deleteCharAt(jsonValue.length - 1)
            }
            return jsonValue.toString()
        }

    }
}
