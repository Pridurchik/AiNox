package org.noxai.base.base


interface StorageClass<UM> {
     fun setUser(user: UM)
     fun getUser() : UM
     fun stateUser() : Boolean
}