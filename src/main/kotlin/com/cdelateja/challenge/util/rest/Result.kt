package com.cdelateja.challenge.util.rest

import com.cdelateja.challenge.util.exception.ServiceException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import java.io.IOException

/**
 * Class in charge of transforming any json to an object or List or map
 */
class Result {

    private val mapper = ObjectMapper()
    private var jsonValue: String = ""
    
    constructor(jsonValue: String) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        this.jsonValue = jsonValue
    }

    companion object {
        private val log = LoggerFactory.getLogger(Result::class.java)
        fun result(jsonValue: String): Result {
            return Result(jsonValue)
        }
    }

    @Throws(ServiceException::class)
    fun <T> toObject(clazz: Class<T>?): T {
        return try {
            mapper.readValue(jsonValue, clazz)
        } catch (e: IOException) {
            log.error(e.message)
            throw ServiceException(e.message)
        }
    }

    @Throws(ServiceException::class)
    fun <T> toList(clazz: Class<T>?): List<T> {
        return try {
            mapper.readValue(jsonValue, mapper.typeFactory.constructCollectionType(MutableList::class.java, clazz))
        } catch (e: IOException) {
            log.error(e.message)
            throw ServiceException(e.message)
        }
    }

    @Throws(ServiceException::class)
    fun <T> toSet(clazz: Class<T>?): Set<T> {
        return try {
            mapper.readValue(jsonValue, mapper.typeFactory.constructCollectionType(MutableList::class.java, clazz))
        } catch (e: IOException) {
            log.error(e.message)
            throw ServiceException(e.message)
        }
    }

    @Throws(ServiceException::class)
    fun <T> toStringMap(clazz: Class<T>?): Map<String?, T> {
        return try {
            mapper.readValue(jsonValue, mapper.typeFactory.constructMapType(MutableMap::class.java, String::class.java, clazz))
        } catch (e: IOException) {
            log.error(e.message)
            throw ServiceException(e.message)
        }
    }

    @Throws(ServiceException::class)
    fun <T> toLongMap(clazz: Class<T>?): Map<Long?, T> {
        return try {
            mapper.readValue(jsonValue, mapper.typeFactory.constructMapType(MutableMap::class.java, Long::class.java, clazz))
        } catch (e: IOException) {
            log.error(e.message)
            throw ServiceException(e.message)
        }
    }

    @Throws(ServiceException::class)
    fun <T> toIntegerMap(clazz: Class<T>?): Map<Int?, T> {
        return try {
            mapper.readValue(jsonValue, mapper.typeFactory.constructMapType(MutableMap::class.java, Int::class.java, clazz))
        } catch (e: IOException) {
            log.error(e.message)
            throw ServiceException(e.message)
        }
    }

    fun getJsonValue(): String {
        return jsonValue
    }
}
