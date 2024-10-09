package com.aichat.features.features_settings.ui

import android.content.Intent
import android.content.Intent.*
import android.net.Uri
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.navigation.fragment.findNavController
import com.aichat.features.R
import org.noxai.base.base.BaseFragment
import org.noxai.features.R
import org.noxai.features.databinding.FragmentSettingsScreenBinding


class SettingsScreenFragment : BaseFragment<FragmentSettingsScreenBinding>(
    R.layout.fragment_settings_screen,
    FragmentSettingsScreenBinding::inflate
) {
    override fun setupUi() = with(binding) {

        cancelSubscribeImage.setOnClickListener {
            addHint(
                it,
                R.layout.hint_cancel_sub,
                700
            )
        }

        rateAppImage.setOnClickListener {

        }

        supportImage.setOnClickListener {
            addHint(
                it,
                R.layout.hint_support_chat,
                700
            )
        }



        comebackImage.setOnClickListener {
            findNavController().navigate(R.id.action_settingsScreenFragment_to_chatFragment)
        }

        political.setOnClickListener {
            openSiteInBrowser(
                "https://ailynx.org/?to=privacy"
            )
        }

        licenzy.setOnClickListener {
            openSiteInBrowser(
                "https://ailynx.org/?to=conditions"
            )
        }

        contract.setOnClickListener {
            openSiteInBrowser(
                "https://ailynx.org/?to=oferta"
            )
        }


        chanel.setOnClickListener {
           openSiteInBrowser(
                "https://t.me/+gSPnlV-nffc0NDJk"
           )
        }

        support.setOnClickListener {
            val intent = Intent(ACTION_SENDTO)
            intent.data = Uri.parse("mailto:info@ailynx.org")
            intent.putExtra(EXTRA_SUBJECT, "Theme of letter")
            intent.putExtra(EXTRA_TEXT, "Text of latter")
            startActivity(intent)
        }

    }


    private fun addHint(
        anchorView: View,
        @LayoutRes resId: Int,
        width: Int = LinearLayout.LayoutParams.WRAP_CONTENT,
        height : Int =  LinearLayout.LayoutParams.WRAP_CONTENT
    ) {
        val inflater = LayoutInflater.from(requireContext())
        val tooltipView = inflater.inflate(resId, null)

        val popupWindow = PopupWindow(
            tooltipView,
            width,
            height
        )

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAsDropDown(anchorView, 0, 0)
    }

    private fun openSiteInBrowser(
        url: String
    ) {
        val intent = Intent(ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

}