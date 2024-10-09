package org.noxai.data.rerepository.storage.model

import org.noxai.base.model.UserBaseModel

data class UserStorage(
    override val email: String,
    override val password: String,
) : UserBaseModel