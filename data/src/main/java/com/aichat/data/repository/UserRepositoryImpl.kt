package org.noxai.data.rerepository.repository

import org.noxai.base.model.UserBaseModel
import org.noxai.base.repository.UserRepository
import org.noxai.data.rerepository.storage.sharedPreferenceStorage.PreferenceStorage
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    var preferenceStorage: PreferenceStorage
): UserRepository {

    override fun saveUser(user: UserBaseModel) {
        preferenceStorage.setUser(user)
    }

    override fun getUser(): UserBaseModel? {
        return preferenceStorage.getUser()
    }

    override fun stateUser(): Boolean {
        return preferenceStorage.stateUser()
    }
}