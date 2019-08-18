package vue.grails.demo

import com.vue.grails.demo.BaseRole
import com.vue.grails.demo.BaseUser
import com.vue.grails.demo.BaseUserBaseRole
import com.vue.grails.demo.Requestmap

class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        createDefaultRoles()
        createDefaultUsers()
        createRequestmap()
    }

    def destroy = {
    }

    private void createDefaultRoles() {
        if (!BaseRole.findByAuthority('ROLE_ADMIN')){
            new BaseRole(authority:'ROLE_ADMIN').save(flush: true)
        }
    }

    private void createDefaultUsers() {
        if (!BaseUser.findByUsername('admin')) {
            def admin = new BaseUser(username:'admin', password: '123456')
            admin.save(flush: true)
            BaseUserBaseRole.create(admin,BaseRole.findByAuthority('ROLE_ADMIN'),true);
        }
    }

    private void createRequestmap() {
        if (Requestmap.count() == 0) {
            for (String url in [
                    '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                    '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**',
                    '/login', '/login.*', '/login/*',
                    '/logout', '/logout.*', '/logout/*',
                    '/api/login', '/api/validate', '/api/logout']) {
                new Requestmap(url: url, configAttribute: 'permitAll').save()
            }

            new Requestmap(url: '/api/**', configAttribute: 'isFullyAuthenticated()').save(flush: true)
            new Requestmap(url: '/**', configAttribute: 'isFullyAuthenticated()').save(flush: true)
            springSecurityService.clearCachedRequestmaps()
        }
    }

}
