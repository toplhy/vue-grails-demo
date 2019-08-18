// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.vue.grails.demo.BaseUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.vue.grails.demo.BaseUserBaseRole'
grails.plugin.springsecurity.authority.className = 'com.vue.grails.demo.BaseRole'
grails.plugin.springsecurity.requestMap.className = 'com.vue.grails.demo.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'


grails.plugin.springsecurity.filterChain.chainMap = [
        //Stateless chain
        [
                pattern: '/**',
                filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
        ],

        //Traditional, stateful chain
        [
                pattern: '/stateful/**',
                filters: 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
        ]
]
grails.plugin.springsecurity.rest.token.storage.jwt.secret = '1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111222222'



