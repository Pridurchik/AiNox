package org.noxai.data.rerepository.model

import org.noxai.base.base.BaseBody

data class Body(
    override val em: String,
    override val pass: String
) : BaseBody
