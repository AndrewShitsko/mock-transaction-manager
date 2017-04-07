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
        def adminRole = new Role(authority: Role.AvailableRoles.ADMIN.value()).save(flush: true)
        new Role(authority: Role.AvailableRoles.USER.value()).save(flush: true)
        def params = [email: "john.doe@realizeideas.net", password: "password", firstName: "John", lastName: "Doe", role: adminRole.authority]

        when:
        def result = service.createUser(params)

        then:
        result
        1 * service.roleService.grantRole(_, _)
        UserRole.findByRole(adminRole)
    }
}
