package com.aichat.features.feature_chat.adapter.menuChat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.aichat.features.R
import com.aichat.features.databinding.MenuChatItemBinding
import com.aichat.features.databinding.MenuNewChatItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.noxai.base.model.UserBaseModel
import com.aichat.features.feature_chat.adapter.chat.ChatCustomRecyclerAdapter
import com.aichat.features.feature_chat.adapter.menuChat.viewHolder.MenuRecyclerViewHolder
import org.noxai.features.feature_chat.adapter.menuChat.MenuChatRecyclerViewItem
import com.aichat.features.feature_chat.domain.repository.ChatUserRepositoryImpl
import com.aichat.features.feature_chat.vm.ChatViewModel

open class MenuChatCustomRecyclerAdapter  constructor(
    private val _list: List<MenuChatRecyclerViewItem>,
    private val viewModel: ChatViewModel,
    private val chatCustomRecyclerAdapter: ChatCustomRecyclerAdapter,
    private val chatUserRepositoryImpl : ChatUserRepositoryImpl,
    private val user: UserBaseModel,
    private val drawer: DrawerLayout,
    private val renameChatName: () -> Unit

) : RecyclerView.Adapter<MenuRecyclerViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuRecyclerViewHolder {
        return when(viewType){
            R.layout.menu_chat_item -> MenuRecyclerViewHolder.MenuChatHolder(
                MenuChatItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.menu_new_chat_item -> MenuRecyclerViewHolder.NewMenuChatHolder(
                MenuNewChatItemBinding.inflate(
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
            is MenuChatRecyclerViewItem.NewChat -> R.layout.menu_new_chat_item
            is MenuChatRecyclerViewItem.SpecificChat -> R.layout.menu_chat_item
        }
    }


    override fun onBindViewHolder(holder: MenuRecyclerViewHolder, position: Int) {

        /**
         *  Обработчик нажатий на item в MenuRecyclerView
        */


        val item : MenuChatRecyclerViewItem = _list[position]

        holder.itemView.setOnClickListener {
            when(item) {
                is MenuChatRecyclerViewItem.NewChat -> {

                }

                is MenuChatRecyclerViewItem.SpecificChat -> {
                    CoroutineScope(Dispatchers.IO).launch {

                        async {
                            viewModel.setMessages(
                                modelOfMessages = chatUserRepositoryImpl.getAllMessagesChat(
                                    user = user,
                                    chat = item.id.toString()),
                                chatCustomRecyclerAdapter,
                                renameChatName
                            )
                        }.await()

                        drawer.closeDrawers()
                    }
                }
            }
        }

        when(holder){
            is MenuRecyclerViewHolder.NewMenuChatHolder -> {
                holder.bind()
            }
            is MenuRecyclerViewHolder.MenuChatHolder -> {
                holder.bind(_list[position] as MenuChatRecyclerViewItem.SpecificChat)
            }

        }
    }

}