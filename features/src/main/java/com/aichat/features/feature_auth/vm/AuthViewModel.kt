package com.aichat.features.feature_auth.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.noxai.base.model.chat_model.model_chat.ModelChat
import org.noxai.base.model.UserBaseModel
import org.noxai.base.repository.AuthUserRepository
import javax.inject.Inject

class AuthViewModel @Inject constructor(

) : ViewModel() {

    private val _liveDataCallAnswer : MutableLiveData<ModelChat> = MutableLiveData()
    val liveDataCallAnswer : LiveData<ModelChat> = _liveDataCallAnswer

    suspend fun sendRequest(
        authUserRepository: AuthUserRepository,
        user: UserBaseModel
    ) {

        viewModelScope.launch {
            _liveDataCallAnswer.value = authUserRepository.authUser(user)

        }


    }

}