package com.aichat.features.feature_pager.ui

import com.aichat.features.R
import com.aichat.features.databinding.FragmentThirdPagerBinding
import org.noxai.base.base.BaseFragment

class ThirdPagerFragment constructor(

): BaseFragment<FragmentThirdPagerBinding>(
    R.layout.fragment_third_pager,
    FragmentThirdPagerBinding::inflate
) {

    val pagerID = 2
    override fun setupUi() {
        val test = 1
    }

}