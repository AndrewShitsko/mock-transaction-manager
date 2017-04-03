package mock.transaction.manager

class Role implements Serializable {

    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }

    @Override
    public String toString() {
        authority
    }

    enum AvailableRoles {
        ADMIN("ROLE_ADMIN"), USER("ROLE_USER")

        String roleId

        private AvailableRoles(String id) {
            this.roleId = id
        }

        String value() {
            roleId
        }
    }
}

