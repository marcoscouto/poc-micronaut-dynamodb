package com.marcoscouto.persistence

import com.marcoscouto.domain.User

interface UserRepository {

    fun getUser(identifier: String): User

    fun saveUser(user: User)

}
