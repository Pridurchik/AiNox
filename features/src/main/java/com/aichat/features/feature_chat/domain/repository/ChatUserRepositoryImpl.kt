package com.aichat.features.feature_chat.domain.repository

import org.noxai.base.model.chat_model.model_chat.ModelChat
import org.noxai.base.model.UserBaseModel
import org.noxai.base.model.chat_model.model_chat.AllMessagesChatModel
import org.noxai.base.model.chat_model.model_chat_question.ModelChatQuestion
import org.noxai.base.model.chat_model.model_current_chat_question.CurrentChatQuestionModel
import org.noxai.base.repository.ChatUserRepository
import org.noxai.data.rerepository.model.AllMessagesOfChatBody
import org.noxai.data.rerepository.model.Body
import org.noxai.data.rerepository.model.CurrentChatQuestionBody
import org.noxai.data.rerepository.model.NewChatQuestionBody
import org.noxai.data.rerepository.retrofit.RetrofitClient
import org.noxai.data.rerepository.retrofit.RetrofitServieces
import javax.inject.Inject

class ChatUserRepositoryImpl @Inject constructor(

) : ChatUserRepository {
    override suspend fun chatUser(user: UserBaseModel?): ModelChat {
        val retrofit = RetrofitClient.getClient("https://aiprofi.net/")

        val productApi = retrofit.create(RetrofitServieces::class.java)

        if (user != null) {
            return productApi.allChatRequest(
                Body(
                    user.email,
                    user.password,
                )
            )

        }

        return ModelChat()
    }

    override suspend fun chatQuestionUser(user: UserBaseModel?, text: String): ModelChatQuestion {
        val retrofit = RetrofitClient.getClient("https://aiprofi.net/")

        val productApi = retrofit.create(RetrofitServieces::class.java)

        if (user != null) {
            return productApi.newChatQuestionRequest(
                NewChatQuestionBody(
                    user.email,
                    user.password,
                    text
                )
            )

        }

        return ModelChatQuestion()
    }

    override suspend fun chatQuestionUserCurrentChat(
        user: UserBaseModel?,
        chat: String,
        text: String,
    ) : CurrentChatQuestionModel {
        val retrofit = RetrofitClient.getClient("https://aiprofi.net/")

        val productApi = retrofit.create(RetrofitServieces::class.java)

        if (user != null) {
            return productApi.currentChatQuestionRequest(
                CurrentChatQuestionBody(
                    user.email,
                    user.password,
                    chat,
                    text
                )
            )

        }

        return CurrentChatQuestionModel()
    }

    override suspend fun getAllMessagesChat(
        user: UserBaseModel?,
        chat: String
    ): AllMessagesChatModel {
        val retrofit = RetrofitClient.getClient("https://aiprofi.net/")

        val productApi = retrofit.create(RetrofitServieces::class.java)

        return productApi.allMessagesCurrentChat(
            AllMessagesOfChatBody(
                user?.email!!,
                user.password,
                chat
            )
        )

    }
}