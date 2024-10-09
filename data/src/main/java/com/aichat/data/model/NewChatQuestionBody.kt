package org.noxai.data.rerepository.model

import org.noxai.base.base.BaseBody

data class NewChatQuestionBody(
    override val em: String,
    override val pass: String,
    val text: String
) : BaseBody
