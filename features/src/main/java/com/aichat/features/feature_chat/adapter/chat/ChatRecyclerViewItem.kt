package com.aichat.features.feature_chat.adapter.chat

sealed class ChatRecyclerViewItem {

    class UserMessage(
        val id: Int,
        val text: String
    ) : ChatRecyclerViewItem()


    class GPTMessage(
        val id : Int,
        val text: String
    ) : ChatRecyclerViewItem()


}