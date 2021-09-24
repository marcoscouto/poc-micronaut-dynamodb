package com.marcoscouto.controller

import com.marcoscouto.domain.User
import com.marcoscouto.persistence.UserRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post

@Controller("/users")
class UserController(private val repository: UserRepository) {

    @Get("/{identifier}")
    fun getUser(@PathVariable identifier: String): HttpResponse<Any> {
        val result = repository.getUser(identifier)
        return HttpResponse.ok(result)
    }

    @Post
    fun getUser(@Body user: User): HttpResponse<Any> {
        repository.saveUser(user)
        return HttpResponse.status(CREATED)
    }

}
