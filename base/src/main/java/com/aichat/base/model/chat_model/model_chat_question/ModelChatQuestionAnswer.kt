package org.noxai.base.model.chat_model.model_chat_question

data class ModelChatQuestionAnswer(
    val type: String,
    val bot: Boolean,
    val time: Long,
    val text: String
)
