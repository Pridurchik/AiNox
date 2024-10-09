package org.noxai.features.feature_regist.data.repository

import kotlinx.coroutines.Dispatchers
import org.noxai.base.model.ModelRegist
import org.noxai.base.model.UserBaseModel
import org.noxai.base.repository.RegistUserRepository
import org.noxai.data.rerepository.model.Body
import org.noxai.data.rerepository.retrofit.RetrofitClient
import org.noxai.data.rerepository.retrofit.RetrofitServieces
import javax.inject.Inject

class RegistUserRepositoryImpl @Inject constructor() : RegistUserRepository {
    override suspend fun registUser(user: UserBaseModel): ModelRegist = with(Dispatchers.IO) {

        val retrofit = RetrofitClient.getClient("https://aiprofi.net/")

        val productApi = retrofit.create(RetrofitServieces::class.java)

        return productApi.registrationRequest(
            Body (
                user.email,
                user.password
            )
        )
    }
}