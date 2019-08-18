package com.vue.grails.demo

import grails.gorm.services.Service

@Service(BaseUser)
interface BaseUserService {

    BaseUser get(Serializable id)

    List<BaseUser> list(Map args)

    Long count()

    void delete(Serializable id)

    BaseUser save(BaseUser baseUser)

}