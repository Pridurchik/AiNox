package com.aichat.features.feature_regist.ui

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aichat.features.R
import com.aichat.features.databinding.FragmentRegistBinding
import kotlinx.coroutines.launch
import org.noxai.base.base.BaseFragment
import org.noxai.data.rerepository.repository.UserRepositoryImpl
import org.noxai.features.DaggerAppComponent
import org.noxai.base.common.ShowBottomSheet
import org.noxai.base.common.ToastMessage
import com.aichat.features.feature_bottomsheet.GoogleBottomSheet
import com.aichat.features.feature_bottomsheet.YandexBottomSheet
import org.noxai.features.feature_regist.data.repository.RegistUserRepositoryImpl
import org.noxai.features.feature_regist.domain.model.User
import org.noxai.features.feature_regist.vm.RegistViewModel
import javax.inject.Inject

class RegistFragment : BaseFragment<FragmentRegistBinding>(
    R.layout.fragment_regist,
    FragmentRegistBinding::inflate,
) {

    @Inject
    lateinit var userRepository: UserRepositoryImpl

    @Inject
    lateinit var registUserRepository: RegistUserRepositoryImpl

    @Inject
    lateinit var viewModel : RegistViewModel


    override fun onAttach(context: Context) {
        DaggerAppComponent.factory().factory(requireContext()).inject(this)
        super.onAttach(context)
    }


    override fun setupUi() = with(binding) {


        /**
         * Обрабатываем UI
        **/

        authTextID.setOnClickListener {
            findNavController().navigate(R.id.action_registFragment_to_authFragment)
        }

        nextBtn.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            lifecycleScope.launch {
                viewModel.sendRequest(
                    user = User(email, password),
                    registUserRepository = registUserRepository
                )

            }

        }

        yandexButton.setOnClickListener {
            ShowBottomSheet.showBottomSheet(parentFragmentManager,
                YandexBottomSheet(requireContext(), R.id.action_registFragment_to_chatFragment)
            )
        }

        googleButton.setOnClickListener {
            ShowBottomSheet.showBottomSheet(parentFragmentManager,
                GoogleBottomSheet(requireContext(), R.id.action_registFragment_to_chatFragment)
            )
        }


        /**
         * Обработка callback чтобы при навигации я попадал в choosingEntryFragment
        **/
        addCallBack()


        /**
         * Добавляем обработку LiveData во ViewModel
        **/

        initLiveDataObserver()

    }

    private fun addCallBack() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_registFragment_to_choosingEntryFragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun initLiveDataObserver() {
        viewModel.livedata.observe(this) {

            when(it.code) {

                80 -> {
                    binding.emailEditTextLayout.helperText =
                        getString(R.string.user_is_not_founded)
                }

                81 -> {
                    binding.passwordEditTextLayout.helperText =
                        getString(R.string.helper_text_incorrect_password)
                }

                83 -> {
                    binding.emailEditTextLayout.helperText =
                        getString(R.string.incorrect_symbol)
                }

                84 -> {
                    binding.emailEditTextLayout.helperText =
                        getString(R.string.incorrect_email_client)
                }

                85 -> {
                    ToastMessage.makeToastLong(requireContext(), "Превышено число попыток регистрации с одного IP")
                }

                86 -> {
                    ToastMessage.makeToastLong(requireContext(), "Превышено число попыток авторизации с одного IP")
                }

                200 -> {
                    userRepository.saveUser(
                        User(
                            email = binding.emailEditText.text.toString(),
                            password = binding.passwordEditText.text.toString()
                        )
                    )

                    findNavController().navigate(R.id.action_registFragment_to_chatFragment)
                }

                500 -> {
                    ToastMessage.makeToastLong(requireContext(), "service_error")
                }

            }
        }
    }
}

