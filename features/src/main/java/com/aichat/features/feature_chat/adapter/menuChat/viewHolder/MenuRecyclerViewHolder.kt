package com.aichat.features.feature_chat.adapter.menuChat.viewHolder

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aichat.features.databinding.MenuChatItemBinding
import com.aichat.features.databinding.MenuNewChatItemBinding
import org.noxai.features.feature_chat.adapter.menuChat.MenuChatRecyclerViewItem

open class MenuRecyclerViewHolder (
    view: View
) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

    class MenuChatHolder(
        private val binding: MenuChatItemBinding
    ) : MenuRecyclerViewHolder(binding.root) {
        fun bind(menuChat: MenuChatRecyclerViewItem.SpecificChat){
            binding.chatName.text = menuChat.text
            binding.morePoint.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?,
        ) {
        }
    }

    class NewMenuChatHolder(
        private val binding: MenuNewChatItemBinding
    ) : MenuRecyclerViewHolder(binding.root) {
        fun bind(){
            // Тут ничего не делаем, текст остается как есть "Новый чат"
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?,
        ) {
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?,
    ) {
    }


}