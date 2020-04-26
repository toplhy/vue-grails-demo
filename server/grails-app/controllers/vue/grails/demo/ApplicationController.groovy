package vue.grails.demo

import grails.converters.JSON
import grails.core.GrailsApplication
import grails.plugins.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }

    def menus() {
        def menus = []
        def map = [:]
        map.id = "1"
        map.text = '系统管理'
        map.url = ''
        def childList = []
        def map2 = [:]
        map2.id = "2"
        map2.text = '用户管理'
        map2.url = '/login'
        map2.children = []
        childList << map2
        map.children = childList
        menus << map
        render menus as JSON
    }
}
