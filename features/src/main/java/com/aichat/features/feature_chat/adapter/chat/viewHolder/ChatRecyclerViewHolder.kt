package com.aichat.features.feature_chat.adapter.chat.viewHolder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.aichat.features.R
import com.aichat.features.databinding.ChatRecyclerviewItemBinding
import com.aichat.features.databinding.ChatRecyclerviewUserItemBinding
import org.noxai.base.common.ToastMessage
import com.aichat.features.feature_chat.adapter.chat.ChatRecyclerViewItem

open class ChatRecyclerViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    class GPTMessageHolder(
        private val binding: ChatRecyclerviewItemBinding
    ) : ChatRecyclerViewHolder(binding.root) {
        fun bind(GptMessage: ChatRecyclerViewItem.GPTMessage, context: Context){
            binding.textViewText.text = GptMessage.text

            binding.copyTextLayoutID.setOnClickListener {
                var myClipboard = getSystemService(context, ClipboardManager::class.java) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText("simple text", GptMessage.text)
                myClipboard.setPrimaryClip(clip)

                ToastMessage.makeToastShort(context, context.getString(R.string.copy_text))
            }
        }
    }

    class GPTUserMessageHolder(
        private val binding: ChatRecyclerviewUserItemBinding
    ) : ChatRecyclerViewHolder(binding.root){
        fun bind(GptUserMessage: ChatRecyclerViewItem.UserMessage, context: Context){
            binding.textViewText.text = GptUserMessage.text


            binding.copyTextLayout.setOnClickListener {
                var myClipboard = getSystemService(context, ClipboardManager::class.java) as ClipboardManager
                val clip: ClipData = ClipData.newPlainText("simple text", GptUserMessage.text)

                myClipboard.setPrimaryClip(clip)

                ToastMessage.makeToastShort(context, context.getString(R.string.copy_text))
            }
        }
    }


}