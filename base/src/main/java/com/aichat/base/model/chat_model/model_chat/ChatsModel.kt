package org.noxai.base.model.chat_model.model_chat

data class ChatsModel(
    val chatId: String,
    val lastMsgTime: Long,
    val chatName: String,
    val msgs: List<MessageModel>
)
