package org.noxai.features.feature_chat.adapter.menuChat

sealed class MenuChatRecyclerViewItem {

    class NewChat(
        val id: Int,
    ) : MenuChatRecyclerViewItem()


    class SpecificChat(
        val id : Int,
        val text: String
    ) : MenuChatRecyclerViewItem()

}