package com.vue.grails.demo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class BaseUserController {

    BaseUserService baseUserService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond baseUserService.list(params), model:[baseUserCount: baseUserService.count()]
    }

    def show(Long id) {
        respond baseUserService.get(id)
    }

    def save(BaseUser baseUser) {
        if (baseUser == null) {
            render status: NOT_FOUND
            return
        }

        try {
            baseUserService.save(baseUser)
        } catch (ValidationException e) {
            respond baseUser.errors, view:'create'
            return
        }

        respond baseUser, [status: CREATED, view:"show"]
    }

    def update(BaseUser baseUser) {
        if (baseUser == null) {
            render status: NOT_FOUND
            return
        }

        try {
            baseUserService.save(baseUser)
        } catch (ValidationException e) {
            respond baseUser.errors, view:'edit'
            return
        }

        respond baseUser, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        baseUserService.delete(id)

        render status: NO_CONTENT
    }
}
