package com.aichat.features.feature_auth.util

import android.content.Context
import androidx.navigation.NavController
import com.aichat.features.databinding.FragmentAuthBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.noxai.base.model.chat_model.model_chat.ModelChat

object CaseHandling {

    suspend fun caseHandlingAndNavigate(
        binding: FragmentAuthBinding,
        modelRegist: ModelChat,
        navController: NavController,
        context: Context
    ) = withContext(Dispatchers.Main){
//        binding.textInputMailLayout.helperText = context.getString(R.string.nothing)
//        binding.textInputPassLayout.helperText = context.getString(R.string.nothing)

//        if (modelRegist.log == "error") {
//            if(modelRegist.msg == "userNotFound") {
//                binding.textInputMailLayout.helperText =
//                    context.getString(R.string.helper_text_incorrect_mail_or_password)
//                binding.textInputPassLayout.helperText =
//                    context.getString(R.string.helper_text_incorrect_mail_or_password)
//            }
//        } else if(modelRegist.log == "success") {
//            navController.navigate(R.id.action_authFragment_to_chatFragment)
//
//        }
    }
}