package org.noxai.data.rerepository.model

import org.noxai.base.base.BaseBody

data class AllMessagesOfChatBody(
    override val em: String,
    override val pass: String,
    val chat : String
) : BaseBody
