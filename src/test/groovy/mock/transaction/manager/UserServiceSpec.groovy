package mock.transaction.manager

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserService)
@Mock([User, Role, UserRole])
class UserServiceSpec extends Specification {

    def setup() {
        service.roleService = Spy(RoleService)
        service.roleService.transactionManager = transactionManager
    }

    def cleanup() {
    }

    void "test create user"() {
        setup:
        def adminRole = new Role(authority: Role.AvailableRoles.ADMIN.name()).save(flush: true)
        def userRole = new Role(authority: Role.AvailableRoles.USER.name()).save(flush: true)

        when:
        service.createUser([email: "john.doe@local.net", password: "password", firstName: "John", lastName: "Doe",
                            role: adminRole.authority])

        then:
        User.get(1)
        1 * service.roleService.grantRole(_, _)
        UserRole.findByRole(adminRole)
    }
}
