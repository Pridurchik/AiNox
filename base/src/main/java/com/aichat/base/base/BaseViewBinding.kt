package org.noxai.base.base

import androidx.viewbinding.ViewBinding

internal abstract class BaseViewBinding<T: ViewBinding> : ViewBinding {
    /**
     * Устанавливаем ViewBinding
    */
    abstract fun setBinding(viewBinding: T)

    /**
     * Получаем ViewBinding
    */
    abstract fun getViewBinding() : T
}