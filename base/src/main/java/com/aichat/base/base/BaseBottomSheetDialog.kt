package org.noxai.base.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BaseBottomSheetDialog<VB: ViewBinding>(
    context: Context,
    @LayoutRes layout: Int,
    private val bindingInflater : (inflater: LayoutInflater) -> VB,
): BottomSheetMarker(layout) {

    private var _binding: VB? = null
    val binding: VB by lazy {  _binding as VB  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setupUi()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    abstract fun setupUi()
}