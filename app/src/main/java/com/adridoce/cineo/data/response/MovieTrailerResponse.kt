package com.adridoce.cineo.data.response

import com.adridoce.cineo.domain.entity.TrailerEntity
import kotlinx.serialization.Serializable

@Serializable
data class MovieTrailerResponse(
    val results: List<TrailerResponse>
)

@Serializable
data class TrailerResponse(
    val key: String,
    val site: String,
    val type: String
)

fun TrailerResponse.toDomain(): TrailerEntity {
    return TrailerEntity(
        key = key,
        site = site,
        type = type
    )
}