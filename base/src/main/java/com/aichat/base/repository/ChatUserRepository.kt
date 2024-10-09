package org.noxai.base.repository

import org.noxai.base.model.chat_model.model_chat.ModelChat
import org.noxai.base.model.UserBaseModel
import org.noxai.base.model.chat_model.model_chat.AllMessagesChatModel
import org.noxai.base.model.chat_model.model_chat_question.ModelChatQuestion
import org.noxai.base.model.chat_model.model_current_chat_question.CurrentChatQuestionModel

interface ChatUserRepository {
    suspend fun chatUser(user: UserBaseModel?): ModelChat
    suspend fun chatQuestionUser(user: UserBaseModel?, text: String): ModelChatQuestion
    suspend fun chatQuestionUserCurrentChat(user: UserBaseModel?, chat: String, text: String) : CurrentChatQuestionModel
    suspend fun getAllMessagesChat(user: UserBaseModel?, chat: String) : AllMessagesChatModel

}