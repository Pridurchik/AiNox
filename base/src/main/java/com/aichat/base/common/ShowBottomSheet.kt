package org.noxai.base.common

import androidx.fragment.app.FragmentManager
import org.noxai.base.base.BottomSheetMarker

object ShowBottomSheet {

    fun showBottomSheet(
        parentFragmentManager: FragmentManager,
        baseBottomSheet: BottomSheetMarker,
        cancelable: Boolean = true
    ) {

        baseBottomSheet.setCancelable(cancelable)
        baseBottomSheet.show(parentFragmentManager, "tag")


    }
}