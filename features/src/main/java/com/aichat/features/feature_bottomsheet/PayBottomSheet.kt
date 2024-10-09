package com.aichat.features.feature_bottomsheet

import android.content.Context
import com.aichat.features.R
import com.aichat.features.databinding.FragmentPayBottomSheetBinding
import org.noxai.base.base.BaseBottomSheetDialog
import org.noxai.base.common.ToastMessage
import javax.inject.Inject

class PayBottomSheet @Inject constructor(
    context: Context
): BaseBottomSheetDialog<FragmentPayBottomSheetBinding>(
    context,
    R.layout.fragment_pay_bottom_sheet,
    FragmentPayBottomSheetBinding::inflate,
) {


    override fun setupUi() = with(binding) {
        idBtnDismiss.setOnClickListener {
            if (checkerPay1.isChecked && checkerPay2.isChecked) {
                PaymentBottomSheet(requireContext()).show(parentFragmentManager, "tag")
            } else {
                ToastMessage.makeToastShort(requireContext(), "Необходимо принять все условия")
            }
        }
    }
}