package org.noxai.features.feature_transaction.domain.model

import org.noxai.base.model.UserBaseModel

data class User(
    override val email: String,
    override val password: String
) : UserBaseModel
