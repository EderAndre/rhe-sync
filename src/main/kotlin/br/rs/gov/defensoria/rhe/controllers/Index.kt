package br.rs.gov.defensoria.rhe.controllers

import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/index")
class Index {
    @Get("/{id}")
    fun getOne(id: Long): MutableHttpResponse<Long>? {
        return  ok(id)
    }

}