package mock.transaction.manager

import grails.transaction.Transactional

@Transactional
class UserService {

    def roleService

    def createUser(Map params) {
        def user = new User(params)
        if (!user.save()) {
            return null
        }
        roleService.grantRole(user, Role.findByAuthority(params.role))
        user
    }
}
