package org.noxai.features.feature_transaction.ui

import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.aichat.features.R
import com.aichat.features.databinding.FragmentPagerBinding
import org.noxai.base.base.BaseFragment
import com.aichat.features.feature_pager.ui.FirstPagerFragment
import com.aichat.features.feature_pager.ui.SecondPagerFragment
import com.aichat.features.feature_pager.ui.ThirdPagerFragment
import com.aichat.features.feature_transaction.domain.adapter.MyViewPagerAdapter

class PagerFragment constructor(

): BaseFragment<FragmentPagerBinding>(
    R.layout.fragment_pager,
    FragmentPagerBinding::inflate
) {

    override fun setupUi(): Unit = with(binding){


        viewPager.adapter = MyViewPagerAdapter(
            fm = parentFragmentManager,
            binding = binding,
            context = requireContext()
        )

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                changeColor()
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}


            fun changeColor() {
                when (binding.viewPager.currentItem) {
                    0 -> {
                        FirstPagerFragment()

                        binding.oneViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_green
                        )

                        binding.twoViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_white
                        )

                        binding.threeViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_white
                        )
                    }

                    1 -> {
                        SecondPagerFragment()
                        binding.oneViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_white
                        )

                        binding.twoViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_green
                        )

                        binding.threeViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_white
                        )
                    }

                    2 -> {
                        ThirdPagerFragment()
                        binding.oneViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_white
                        )

                        binding.twoViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_white
                        )

                        binding.threeViewViewPager.background = AppCompatResources.getDrawable(
                            requireContext(), R.drawable.bg_viewpager_tablayout_green
                        )

                    }
                }
            }

        })


        nextBtn.setOnClickListener {
            if (viewPager.currentItem == 2) {
                findNavController().navigate(R.id.action_pagerFragment_to_choosingEntryFragment)
            }
            viewPager.currentItem += 1
        }




    }


}