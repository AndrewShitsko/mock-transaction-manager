package mock.transaction.manager

import grails.gorm.DetachedCriteria

class UserRole implements Serializable {

    User user
    Role role

    static mapping = {
        id composite: ['user', 'role']
        version false
    }

    static constraints = {
        role validator: { Role r, UserRole ur ->
            if (ur.user?.id) {
                UserRole.withNewSession {
                    if (UserRole.exists(ur.user.id, r.id)) {
                        return ['userRole.exists']
                    }
                }
            }
        }
    }

    static boolean exists(long userId, long roleId) {
        criteriaFor(userId, roleId).count()
    }

    private static DetachedCriteria criteriaFor(long userId, long roleId) {
        UserRole.where { user == User.load(userId) && role == Role.load(roleId) }
    }
}
