package com.cdelateja.challenge.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

@JsonIgnoreProperties(ignoreUnknown = true)
class PageSaveResponse(
        @JsonProperty("data")
        val data: PageSaveData) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PageSaveData(
        @JsonProperty("type")
        val type: String,
        @JsonProperty("id")
        val id: String,
        @JsonProperty("attributes")
        val attributes: PageSaveAttributes) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PageSaveAttributes(
        @JsonProperty("name")
        val name: String,
        ) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
