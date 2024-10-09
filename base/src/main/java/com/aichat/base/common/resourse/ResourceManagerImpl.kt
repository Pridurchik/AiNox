package org.noxai.base.common.resourse

import android.content.Context
import org.noxai.base.common.resourse.ResourceManager
//import javax.inject.Inject

class ResourceManagerImpl  constructor(
    private val context : Context
) : ResourceManager {
    override fun getString(resourceId: Int): String {
        return context.getString(resourceId)
    }

    override fun getColor(resourceId: Int): Int {
        return context.getColor(resourceId)
    }
}