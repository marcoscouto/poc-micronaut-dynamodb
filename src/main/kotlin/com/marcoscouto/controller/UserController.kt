package com.marcoscouto.controller

import com.marcoscouto.domain.User
import com.marcoscouto.persistence.UserRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.OK
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

@Controller("/users")
class UserController(private val repository: UserRepository) {

    @Get("/{identifier}")
    fun getUser(@PathVariable identifier: String): HttpResponse<Any> {
        val result = repository.getUser(identifier) ?: return HttpResponse.notFound()
        return HttpResponse.ok(result)
    }

    @Post
    fun saveUser(@Body user: User): HttpResponse<Any> {
        repository.saveUser(user)
        return HttpResponse.status(CREATED)
    }

    @Put("/{identifier}")
    fun saveUser(@PathVariable identifier: String, @Body user: User): HttpResponse<Any> {
        repository.getUser(identifier) ?: return HttpResponse.notFound()
        repository.saveUser(user)
        return HttpResponse.status(OK)
    }

    @Delete("/{identifier}")
    fun deleteUser(@PathVariable identifier: String): HttpResponse<Any> {
        repository.getUser(identifier) ?: return HttpResponse.notFound()
        repository.deleteUser(identifier)
        return HttpResponse.status(OK)
    }

}
