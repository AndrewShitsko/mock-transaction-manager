package mock.transaction.manager

class BootStrap {

    def init = { servletContext ->
        Role.AvailableRoles.values().each {
            if (!Role.countByAuthority(it.value())) {
                new Role(authority: it.value()).save()
            }
        }
    }
    def destroy = {
    }
}
