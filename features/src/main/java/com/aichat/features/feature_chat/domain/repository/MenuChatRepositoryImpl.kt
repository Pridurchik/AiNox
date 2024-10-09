package org.noxai.features.feature_chat.domain.repository

import org.noxai.base.model.UserBaseModel
import org.noxai.base.model.chat_model.model_chat.ModelChat
import org.noxai.base.repository.MenuChatRepository
import org.noxai.data.rerepository.model.Body
import org.noxai.data.rerepository.retrofit.RetrofitClient
import org.noxai.data.rerepository.retrofit.RetrofitServieces
import javax.inject.Inject

class MenuChatRepositoryImpl @Inject constructor(

) : MenuChatRepository {
    override suspend fun updateChat(
        user: UserBaseModel
    ): ModelChat {
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
}