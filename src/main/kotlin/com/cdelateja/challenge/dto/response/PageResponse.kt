package com.cdelateja.challenge.dto.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle

@JsonIgnoreProperties(ignoreUnknown = true)
class PageResponse(
        @JsonProperty("data")
        val data: List<Data>) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data(
        @JsonProperty("type")
        val type: String,
        @JsonProperty("id")
        val id: String,
        @JsonProperty("attributes")
        val attributes: Attributes) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Attributes(
        @JsonProperty("title")
        val title: String,
        @JsonProperty("url")
        val url: String,
        @JsonProperty("amp")
        val amp: Boolean,
        @JsonProperty("pageType")
        val pageType: String,
        @JsonProperty("pageStatus")
        val pageStatus: String,
        @JsonProperty("group")
        val group: String?,
        @JsonProperty("order")
        val order: Int,
        @JsonProperty("deleted")
        val deleted: Boolean) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
