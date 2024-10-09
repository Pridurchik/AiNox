package org.noxai.features.feature_auth.data.model

data class StateRequestModel(
    val log: String? = null,
    val msg: String? = null,
    val user_id: Int? = null,
    val sub: String? = null,
    val timestamp: Int? = null
)
