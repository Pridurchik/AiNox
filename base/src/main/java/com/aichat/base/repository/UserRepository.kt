package org.noxai.base.repository

import org.noxai.base.model.UserBaseModel

interface UserRepository {
    fun saveUser(user: UserBaseModel)
    fun getUser() : UserBaseModel?
    fun stateUser() : Boolean
}