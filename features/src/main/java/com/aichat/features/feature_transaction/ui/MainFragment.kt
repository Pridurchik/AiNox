package org.noxai.features.feature_transaction.ui

import android.content.Context
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aichat.features.R
import com.aichat.features.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import org.noxai.base.base.BaseFragment
import org.noxai.data.rerepository.repository.UserRepositoryImpl
import org.noxai.features.DaggerAppComponent
import com.aichat.features.feature_chat.domain.repository.ChatUserRepositoryImpl
import org.noxai.features.feature_transaction.progressBar.InitProgressBar
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(
    R.layout.fragment_main,
    FragmentMainBinding::inflate
) {

    @Inject
    lateinit var userRepository: UserRepositoryImpl

    @Inject
    lateinit var chatUserRepository: ChatUserRepositoryImpl

    override fun onAttach(context: Context) {
        DaggerAppComponent.factory().factory(requireContext()).inject(this)
        super.onAttach(context)
    }

    override fun setupUi() {

        lifecycleScope.launch {

            /**
             * Функция для анализа и навигации юзера в зависимости вощел он или нет
            */

            InitProgressBar
                .navigateMainFrag(
                    userRepository,
                    findNavController(),
                    binding.progressBar
                )
        }

    }
}
