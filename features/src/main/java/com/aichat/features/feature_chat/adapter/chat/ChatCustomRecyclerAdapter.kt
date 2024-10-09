package com.aichat.features.feature_chat.adapter.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aichat.features.R
import com.aichat.features.databinding.ChatRecyclerviewItemBinding
import com.aichat.features.databinding.ChatRecyclerviewUserItemBinding
import org.noxai.base.model.UserBaseModel
import com.aichat.features.feature_chat.adapter.chat.viewHolder.ChatRecyclerViewHolder
import com.aichat.features.feature_chat.domain.repository.ChatUserRepositoryImpl
import com.aichat.features.feature_chat.vm.ChatViewModel

class ChatCustomRecyclerAdapter constructor(
    private val _list: List<ChatRecyclerViewItem>,
    private val viewModel: ChatViewModel,
    private val chatUserRepositoryImpl : ChatUserRepositoryImpl,
    private val user: UserBaseModel,
    private val context: Context
) : RecyclerView.Adapter<ChatRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRecyclerViewHolder {
        return when(viewType){
            R.layout.chat_recyclerview_item -> ChatRecyclerViewHolder.GPTMessageHolder(
                ChatRecyclerviewItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.chat_recyclerview_user_item -> ChatRecyclerViewHolder.GPTUserMessageHolder(
                ChatRecyclerviewUserItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun getItemCount(): Int {
        return _list.size
    }


    override fun getItemViewType(position: Int): Int {
        return when(_list[position]){
            is ChatRecyclerViewItem.GPTMessage -> R.layout.chat_recyclerview_item
            is ChatRecyclerViewItem.UserMessage -> R.layout.chat_recyclerview_user_item
        }
    }

    override fun onBindViewHolder(holder: ChatRecyclerViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            when(holder) {
                is ChatRecyclerViewHolder.GPTMessageHolder -> {

                }

                is ChatRecyclerViewHolder.GPTUserMessageHolder -> {

                }
            }
        }

        when(holder){
            is ChatRecyclerViewHolder.GPTMessageHolder -> holder.bind(_list[position] as ChatRecyclerViewItem.GPTMessage, context)
            is ChatRecyclerViewHolder.GPTUserMessageHolder -> holder.bind(_list[position] as ChatRecyclerViewItem.UserMessage, context)
        }
    }

}