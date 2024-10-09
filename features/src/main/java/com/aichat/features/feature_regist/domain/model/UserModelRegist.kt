package org.noxai.features.feature_regist.domain.model

import org.noxai.base.model.ModelRegist
import org.noxai.base.model.UserBaseModel

data class UserModelRegist(
    override val email: String,
    override val password: String,
    val modelAuth: ModelRegist
) : UserBaseModel
