package org.noxai.features.feature_regist.di.factory

import dagger.assisted.AssistedFactory
import org.noxai.base.model.UserBaseModel
import org.noxai.data.rerepository.storage.sharedPreferenceStorage.PreferenceStorage


@AssistedFactory
interface PreferenceStorageFactory {
    fun create(user: UserBaseModel): PreferenceStorage
}