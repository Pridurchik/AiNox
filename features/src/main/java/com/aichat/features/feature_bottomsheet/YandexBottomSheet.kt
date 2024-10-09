package com.aichat.features.feature_bottomsheet

import android.content.Context
import android.net.Uri
import android.support.annotation.IdRes
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.aichat.features.R
import com.aichat.features.databinding.FragmentYandexBottomSheetBinding
import org.noxai.base.base.BaseBottomSheetDialog
import org.noxai.base.common.ToastMessage
import org.noxai.data.rerepository.repository.UserRepositoryImpl
import org.noxai.features.DaggerAppComponent
import org.noxai.features.feature_auth.domain.model.User
import javax.inject.Inject

class YandexBottomSheet constructor(
    context: Context,
    @IdRes private val navRes: Int
) : BaseBottomSheetDialog<FragmentYandexBottomSheetBinding>(
    context,
    R.layout.fragment_yandex_bottom_sheet,
    FragmentYandexBottomSheetBinding::inflate
) {

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl

    override fun onAttach(context: Context) {
        DaggerAppComponent.factory().factory(requireContext()).inject(this)
        super.onAttach(context)
    }

    override fun setupUi() = with(binding) {
        initWebView()
    }

    private fun initWebView() {
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url == "https://aiprofi.net/app.php?status=error") {
                    ToastMessage.makeToastLong(
                        requireContext(),
                        getString(R.string.something_went_wrong)
                    )
                    binding.webView.loadUrl("https://aiprofi.net/login_ya.php?go=1")
                    return true
                } else if (url?.contains("status=ok") == true) {
                    val uri = Uri.parse(url)
                    val emailId = uri.getQueryParameter("em").toString()
                    val passId = uri.getQueryParameter("pass").toString()

                    userRepositoryImpl.saveUser(
                        User(
                            emailId,
                            passId
                        )
                    )

                    this@YandexBottomSheet.dismiss()

                    findNavController().navigate(navRes)

                    return false
                } else {
                    return false
                }
            }
        }

        binding.webView.requestFocus(View.FOCUS_DOWN)
        binding.webView.loadUrl("https://aiprofi.net/login_ya.php?go=1")

    }

}