package Kinderel.model;

import javax.persistence.*;

@Entity
@Table(name="usersTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersTable_generator")
    @SequenceGenerator(name = "users_generator", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName", nullable=false, unique=true)
    private String userName;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @ManyToOne(targetEntity = Role.class, fetch=FetchType.LAZY)
    @JoinColumn(name = "rolesTable", referencedColumnName = "role_id")
    private Role role;

    public User() {
    }

    public User(String userName, String email, String password, Role role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

