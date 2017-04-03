package mock.transaction.manager

class User implements Serializable {

    String email
    String password
    String firstName
    String lastName

    Date dateCreated
    Date lastUpdated

    static mapping = {
        password column: '`password`'
    }

    static constraints = {
        email blank: false, unique: true, email: true
        password blank: false, password: true
        lastName nullable: true
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role }
    }

    def beforeInsert() {
        encodePassword()
    }

    protected void encodePassword() {
        password = password.encodeAsSHA256()
    }
}
