package org.noxai.features.feature_auth.ui

import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aichat.features.R
import com.aichat.features.databinding.FragmentAuthBinding
import kotlinx.coroutines.launch
import org.noxai.base.base.BaseFragment
import org.noxai.base.common.ToastMessage
import org.noxai.data.rerepository.repository.UserRepositoryImpl
import org.noxai.features.DaggerAppComponent
import org.noxai.features.feature_auth.data.repository.AuthUserRepositoryImpl
import org.noxai.features.feature_auth.domain.model.User
import com.aichat.features.feature_auth.vm.AuthViewModel
import org.noxai.base.common.ShowBottomSheet
import com.aichat.features.feature_bottomsheet.GoogleBottomSheet
import com.aichat.features.feature_bottomsheet.YandexBottomSheet
import javax.inject.Inject

class AuthFragment : BaseFragment<FragmentAuthBinding>(
    R.layout.fragment_auth,
    FragmentAuthBinding::inflate
) {

    @Inject
    lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Inject
    lateinit var authUserRepository: AuthUserRepositoryImpl

    override fun onAttach(context: Context) {
        DaggerAppComponent.factory().factory(requireContext()).inject(this)
        super.onAttach(context)
    }

    override fun setupUi() = with(binding) {

        authBtn.setOnClickListener {
            val pass = binding.passwordEditText.text.toString()
            val email = binding.emailEditText.text.toString()

            lifecycleScope.launch {

                /**
                 * Отправляем запрос на аунтификацию
                */
                viewModel.sendRequest(authUserRepository, User(
                    email = email,
                    password = pass
                ))

            }
        }

        registTextviewID.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registFragment)
        }

        googleButton.setOnClickListener {
            ShowBottomSheet.showBottomSheet(parentFragmentManager,
                    GoogleBottomSheet(requireContext(), R.id.action_authFragment_to_chatFragment)
            )
        }

        yandexButton.setOnClickListener {
            ShowBottomSheet.showBottomSheet(parentFragmentManager,
                YandexBottomSheet(requireContext(), R.id.action_authFragment_to_chatFragment)
            )
        }

        viewModel.liveDataCallAnswer.observe(this@AuthFragment, Observer {

            /**
             * Обработка кода из ответа от сервера
            */

            when(it.code) {

                1 -> {
                    if(binding.emailEditText.text.toString().isEmpty()
                        && binding.passwordEditText.text.toString().isEmpty()
                        ) {
                        binding.emailTextInputLayout.helperText = "Введите свой email"
                        binding.passwordTextInputLayout.helperText = "Введите пароль"
                    }
                    else if(binding.emailEditText.text.toString().isEmpty()) {
                        binding.emailTextInputLayout.helperText = "Введите свой email"
                        binding.passwordTextInputLayout.helperText = ""
                    }
                    else if(binding.passwordEditText.text.toString().isEmpty()){
                        binding.emailTextInputLayout.helperText = ""
                        binding.passwordTextInputLayout.helperText = "Введите пароль"
                    }
                }

                80 -> {
                    binding.emailTextInputLayout.helperText = ""
                    binding.passwordTextInputLayout.helperText = ""
                    ToastMessage.makeToastLong(requireContext(), "Пользователя не существует")
                }

                81 -> {
                    binding.emailTextInputLayout.helperText = ""
                    binding.passwordTextInputLayout.helperText = "Неверный пароль"
                }

                83 -> {
                    binding.passwordTextInputLayout.helperText = ""
                    binding.emailTextInputLayout.helperText = "Неккоректный email"
                }

                200 -> {
                    userRepositoryImpl.saveUser(
                        User(
                            email = binding.emailEditText.text.toString(),
                            password = binding.passwordEditText.text.toString()
                        )
                    )

                    findNavController().navigate(R.id.action_authFragment_to_chatFragment)
                }

                500 -> {
                    binding.emailTextInputLayout.helperText = ""
                    binding.passwordTextInputLayout.helperText = ""

                    ToastMessage.makeToastLong(requireContext(), "Cервисная ошибка")
                }
            }
        })

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_authFragment_to_choosingEntryFragment)
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }





}