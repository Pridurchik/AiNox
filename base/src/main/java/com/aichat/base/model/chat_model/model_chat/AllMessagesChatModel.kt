package org.noxai.base.model.chat_model.model_chat

data class AllMessagesChatModel(
    val log: String? = null,
    val msg: String? = null,
    val code: Int? = null,
    val user_id: Int? = null,
    val timestamp: Long? = null,
    val email: String? = null,
    val pass: String? = null,
    val sub: String? = null,
    val chatId: Long? = null,
    val lastMsgTime: Long? = null,
    val chatName: String? = null,
    val msgs: List<MessageModel>? = null
)
