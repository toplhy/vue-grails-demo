package com.vue.grails.demo

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BaseUserServiceSpec extends Specification {

    BaseUserService baseUserService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new BaseUser(...).save(flush: true, failOnError: true)
        //new BaseUser(...).save(flush: true, failOnError: true)
        //BaseUser baseUser = new BaseUser(...).save(flush: true, failOnError: true)
        //new BaseUser(...).save(flush: true, failOnError: true)
        //new BaseUser(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //baseUser.id
    }

    void "test get"() {
        setupData()

        expect:
        baseUserService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<BaseUser> baseUserList = baseUserService.list(max: 2, offset: 2)

        then:
        baseUserList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        baseUserService.count() == 5
    }

    void "test delete"() {
        Long baseUserId = setupData()

        expect:
        baseUserService.count() == 5

        when:
        baseUserService.delete(baseUserId)
        sessionFactory.currentSession.flush()

        then:
        baseUserService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        BaseUser baseUser = new BaseUser()
        baseUserService.save(baseUser)

        then:
        baseUser.id != null
    }
}
