package org.noxai.base.model.chat_model.model_current_chat_question

data class AnswerCurrentChatQuestionModel(
    val type: String,
    val bot: Boolean,
    val time: Long,
    val text: String
)
