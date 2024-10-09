package org.noxai.base.model.chat_model.model_chat

data class MessageModel(
    val type: String,
    val bot: Boolean,
    val time: Long,
    val text: String
)
