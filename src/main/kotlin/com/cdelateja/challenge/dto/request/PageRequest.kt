package com.cdelateja.challenge.dto.request


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

@JsonIgnoreProperties(ignoreUnknown = true)
class PageRequest(
        @JsonProperty("data")
        val data: DataPage) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}


@JsonIgnoreProperties(ignoreUnknown = true)
class DataPage(
        @JsonProperty("type")
        val type: String,
        @JsonProperty("attributes")
        val attributes: AttributesPage) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}


@JsonIgnoreProperties(ignoreUnknown = true)
class AttributesPage(
        @JsonProperty("name")
        val name: String,
        @JsonProperty("templateId")
        val templateId: Int) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
