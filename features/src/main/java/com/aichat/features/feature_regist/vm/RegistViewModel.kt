package org.noxai.features.feature_regist.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.noxai.base.model.ModelRegist
import org.noxai.base.model.UserBaseModel
import org.noxai.base.repository.RegistUserRepository
import javax.inject.Inject

class RegistViewModel @Inject constructor(

) : ViewModel() {


    private val _ld: MutableLiveData<ModelRegist> = MutableLiveData()
    val livedata : LiveData<ModelRegist> = _ld


    suspend fun sendRequest(
        registUserRepository: RegistUserRepository,
        user: UserBaseModel
    ) {
        _ld.value = registUserRepository.registUser(user)
    }

}