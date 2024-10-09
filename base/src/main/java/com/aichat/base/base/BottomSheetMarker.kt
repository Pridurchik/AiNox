package org.noxai.base.base

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BottomSheetMarker(
    @LayoutRes layout: Int,
) : BottomSheetDialogFragment(layout)