package com.aichat.features.feature_bottomsheet

import android.content.Context
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.aichat.features.R
import com.aichat.features.databinding.FragmentPaymentBottomSheetBinding
import org.noxai.base.base.BaseBottomSheetDialog
import org.noxai.data.rerepository.repository.UserRepositoryImpl
import org.noxai.features.DaggerAppComponent
import javax.inject.Inject


class PaymentBottomSheet @Inject constructor(
    context: Context,
) : BaseBottomSheetDialog<FragmentPaymentBottomSheetBinding>(
    context,
    R.layout.fragment_payment_bottom_sheet,
    FragmentPaymentBottomSheetBinding::inflate,
) {

    @Inject
    lateinit var userRepositoryImpl: UserRepositoryImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.factory().factory(requireContext()).inject(this)
        super.onCreate(savedInstanceState)
        setupUi()
    }


    override fun setupUi() = with(binding) {
        initWebView()
    }

    private fun initWebView() {
        val webSettings: WebSettings = binding.webView.settings
        webSettings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return super.shouldOverrideUrlLoading(view, url)
            }
        }
        binding.webView.loadUrl("https://to-ads.click/HTR5qynW?email=${userRepositoryImpl.getUser()?.email}")

    }


}