package org.noxai.base.common.navigation

import androidx.fragment.app.Fragment
import org.noxai.base.base.BaseFragment

interface FragmentReplacerPager {
    fun replace(position: Int, newFragment: Fragment, isNotify: Boolean = true)
    fun replaceDef(position: Int, isNotify: Boolean = true) : Fragment
}