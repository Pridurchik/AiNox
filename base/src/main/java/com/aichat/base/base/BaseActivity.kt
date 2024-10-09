package org.noxai.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding>(
    @LayoutRes layoutRes: Int,
    private val bindingInflater : (inflater: LayoutInflater) -> VB,
) : AppCompatActivity(layoutRes) {

    private var _binding: VB? = null
    val binding: VB by lazy {
        _binding as VB
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        _binding = bindingInflater.invoke(layoutInflater)
        setupUX()
    }

    fun toHideProgressBar(pb: ProgressBar) {
        pb.visibility = View.GONE

    }

    // Устанавливаем обработку UX
     abstract fun setupUX()


}