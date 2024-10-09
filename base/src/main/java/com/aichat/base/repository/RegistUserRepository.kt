package org.noxai.base.repository

import org.noxai.base.model.ModelRegist
import org.noxai.base.model.UserBaseModel

interface RegistUserRepository {
    suspend fun registUser(user: UserBaseModel) : ModelRegist
}