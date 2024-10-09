package org.noxai.base.repository

import org.noxai.base.model.chat_model.model_chat.ModelChat
import org.noxai.base.model.UserBaseModel

interface AuthUserRepository {

    suspend fun authUser(user: UserBaseModel): ModelChat
}