package com.aichat.features.feature_pager.ui

import com.aichat.features.R
import com.aichat.features.databinding.FragmentSecondPagerBinding
import org.noxai.base.base.BaseFragment

class SecondPagerFragment constructor(

): BaseFragment<FragmentSecondPagerBinding>(
    R.layout.fragment_second_pager,
    FragmentSecondPagerBinding::inflate
) {

    val pagerID = 1

    override fun setupUi() {
        val test = 1
    }

}