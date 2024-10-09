package com.aichat.features.feature_regist.util

import android.content.Context
import androidx.fragment.app.Fragment
import com.aichat.features.R
import com.aichat.features.databinding.FragmentRegistBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.noxai.base.model.ModelRegist

object CaseHandling {

    suspend fun caseHandlingAndNavigate(
        binding: FragmentRegistBinding,
        modelRegist: ModelRegist,
        fragment: Fragment,
        context: Context
    ) = withContext(Dispatchers.Main){
        binding.emailEditText.setText(context.getString(R.string.nothing))
        binding.passwordEditText.setText(context.getString(R.string.nothing))

//        binding.authTextID.setOnClickListener {
//
//        }
    }
}