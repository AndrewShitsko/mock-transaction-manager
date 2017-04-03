package mock.transaction.manager

import grails.transaction.Transactional

@Transactional
class RoleService {

    def grantRole(User user, Role role) {
        def userRoleInstance = new UserRole(user: user, role: role)
        userRoleInstance.save()
    }

    def revokeRole(User u, Role r) {
        if (u != null && r != null) {
            UserRole.where { user == u && role == r }.deleteAll()
        }
    }
}
