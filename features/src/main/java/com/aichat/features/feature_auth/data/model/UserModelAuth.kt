package org.noxai.features.feature_auth.data.model

import org.noxai.base.model.ModelRegist
import org.noxai.base.model.UserBaseModel

data class UserModelAuth(
    override val email: String,
    override val password: String,
    val modelAuth: ModelRegist
) : UserBaseModel
