package com.cdelateja.challenge.dto.request


import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.commons.lang3.builder.ToStringStyle
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
class PageRequest(
        @field:Valid
        @field:NotNull
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
        @field:Valid
        @JsonProperty("attributes")
        val attributes: AttributesPage) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}


@JsonIgnoreProperties(ignoreUnknown = true)
class AttributesPage(
        @field:NotBlank
        @JsonProperty("name")
        val name: String?,
        @field:NotNull
        @JsonProperty("templateId")
        val templateId: Int?) {

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE)
    }
}
