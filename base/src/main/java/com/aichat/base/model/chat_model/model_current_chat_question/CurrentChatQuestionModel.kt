package org.noxai.base.model.chat_model.model_current_chat_question

data class CurrentChatQuestionModel(
    val log: String? = null,
    val msg: String? = null,
    val code: Int? = null,
    val user_id: Int? = null,
    val timestamp: Long? = null,
    val email: String? = null,
    val pass: String? = null,
    val sub: String? = null,
    val chatid: Long? = null,
    val lastMsgTime: Long? = null,
    val chatName: String? = null,
    val chat_id: Long? = null,
    val userSentMsgTime: Long? = null,
    val answer: AnswerCurrentChatQuestionModel? = null
)
