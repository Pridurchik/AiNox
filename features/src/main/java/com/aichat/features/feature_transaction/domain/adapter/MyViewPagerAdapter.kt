package com.aichat.features.feature_transaction.domain.adapter

import android.content.Context
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aichat.features.R
import com.aichat.features.databinding.FragmentPagerBinding
import org.noxai.base.common.ToastMessage
import com.aichat.features.feature_pager.ui.FirstPagerFragment
import com.aichat.features.feature_pager.ui.SecondPagerFragment
import com.aichat.features.feature_pager.ui.ThirdPagerFragment

class MyViewPagerAdapter(
    fm: FragmentManager,
    private val binding: FragmentPagerBinding,
    private val context: Context,
) : FragmentPagerAdapter(fm) {

    private val fragmentList: List<Fragment>? = null
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                FirstPagerFragment()
            }

            1 -> {
                SecondPagerFragment()
            }

            2 -> {
                ThirdPagerFragment()
            }

            else -> {
                Log.e("None fragment", "Fragment not founded")
                FirstPagerFragment()
            }


        }
    }


    override fun getItemPosition(`object`: Any): Int {
        return when(`object`) {
            0 -> {
                ToastMessage.makeToastLong(context, `object`.toString())
                binding.oneViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_green
                )

                binding.twoViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_white
                )

                binding.threeViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_white
                )
                return 0
            }

            1 -> {
                ToastMessage.makeToastLong(context, `object`.toString())
                binding.oneViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_white
                )

                binding.twoViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_green
                )

                binding.threeViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_white
                )
                return 1
            }

            2 -> {
                ToastMessage.makeToastLong(context, `object`.toString())
                binding.oneViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_white
                )

                binding.twoViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_white
                )

                binding.threeViewViewPager.background = AppCompatResources.getDrawable(
                    context, R.drawable.bg_viewpager_tablayout_green
                )
                return 2
            }


            else -> {
                return 0
            }
        }
    }


}