package org.noxai.base.common

import android.content.Context
import android.widget.Toast

object ToastMessage {
    fun makeToastShort(context : Context, textMessage : String) {
        Toast.makeText(context, textMessage, Toast.LENGTH_SHORT).show()
    }

    fun makeToastLong(context : Context, textMessage : String) {
        Toast.makeText(context, textMessage, Toast.LENGTH_LONG).show()
    }
}