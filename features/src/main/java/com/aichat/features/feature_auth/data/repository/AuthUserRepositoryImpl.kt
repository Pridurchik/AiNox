package org.noxai.features.feature_auth.data.repository

import org.noxai.base.model.chat_model.model_chat.ModelChat
import org.noxai.base.model.UserBaseModel
import org.noxai.base.repository.AuthUserRepository
import org.noxai.data.rerepository.model.Body
import org.noxai.data.rerepository.retrofit.RetrofitClient
import org.noxai.data.rerepository.retrofit.RetrofitServieces
import javax.inject.Inject

class AuthUserRepositoryImpl @Inject constructor() : AuthUserRepository {
    override suspend fun authUser(user: UserBaseModel): ModelChat {

        try {
            val retrofit = RetrofitClient.getClient("https://aiprofi.net/")
            val productApi = retrofit.create(RetrofitServieces::class.java)

            return productApi.authRequest(
                Body(
                    user.email,
                    user.password,
                )
            )

        } catch (e: Exception) {
            return ModelChat()
        }
    }

}