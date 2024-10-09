package org.noxai.base.repository

import org.noxai.base.model.UserBaseModel
import org.noxai.base.model.chat_model.model_chat.ModelChat

interface MenuChatRepository {
    suspend fun updateChat(user: UserBaseModel) : ModelChat
}