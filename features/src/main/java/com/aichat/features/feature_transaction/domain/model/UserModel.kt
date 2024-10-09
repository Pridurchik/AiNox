package org.noxai.features.feature_transaction.domain.model

import org.noxai.base.model.UserBaseModel


data class UserModel(
    override val email: String,
    override val password: String
) : UserBaseModel