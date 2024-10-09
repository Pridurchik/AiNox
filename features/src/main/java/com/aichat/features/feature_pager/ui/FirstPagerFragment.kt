package com.aichat.features.feature_pager.ui

import com.aichat.features.R
import com.aichat.features.databinding.FragmentFirstPagerBinding
import org.noxai.base.base.BaseFragment

class FirstPagerFragment constructor(

): BaseFragment<FragmentFirstPagerBinding>(
    R.layout.fragment_first_pager,
    FragmentFirstPagerBinding::inflate
) {

    val pagerID = 0


    override fun setupUi() {
        val test = 1
    }

}