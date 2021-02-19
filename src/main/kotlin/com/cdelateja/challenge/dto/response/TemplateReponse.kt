package com.cdelateja.challenge.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

@JsonIgnoreProperties(ignoreUnknown = true)
class TemplateResponse(
        @JsonProperty("data")
        val data: List<DataTemplate>) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class DataTemplate(
        @JsonProperty("type")
        val type: String,
        @JsonProperty("id")
        val id: String,
        @JsonProperty("attributes")
        val attributes: AttributesTemplate) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class AttributesTemplate(
        @JsonProperty("pageId")
        val pageId: Int,
        @JsonProperty("name")
        val name: String,
        @JsonProperty("imageUrl")
        val imageUrl: String,
        @JsonProperty("categories")
        val categories: List<String?>?,
        @JsonProperty("blankPage")
        val blankPage: Boolean) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
