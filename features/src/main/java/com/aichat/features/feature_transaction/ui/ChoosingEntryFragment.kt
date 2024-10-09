package org.noxai.features.feature_transaction.ui

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.aichat.features.R
import com.aichat.features.databinding.FragmentChoosingEntryBinding
import org.noxai.base.base.BaseFragment
import org.noxai.base.common.ShowBottomSheet
import com.aichat.features.feature_bottomsheet.GoogleBottomSheet
import com.aichat.features.feature_bottomsheet.YandexBottomSheet


class ChoosingEntryFragment : BaseFragment<FragmentChoosingEntryBinding>(
    R.layout.fragment_choosing_entry,
    FragmentChoosingEntryBinding::inflate
) {

    override fun setupUi() = with(binding) {
        registBtn.setOnClickListener {
            findNavController().navigate(R.id.action_choosingEntryFragment_to_registFragment)
        }

        authText.setOnClickListener {
            findNavController().navigate(R.id.action_choosingEntryFragment_to_authFragment)
        }

        googleButton.setOnClickListener {
            ShowBottomSheet.showBottomSheet(
                parentFragmentManager,
                GoogleBottomSheet(requireContext(), R.id.action_choosingEntryFragment_to_chatFragment)
            )

        }

        yandexButton.setOnClickListener {
            ShowBottomSheet.showBottomSheet(parentFragmentManager,
                YandexBottomSheet(requireContext(), R.id.action_choosingEntryFragment_to_chatFragment)
            )
        }

        addCallBackPressed()
    }

    private fun addCallBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

}