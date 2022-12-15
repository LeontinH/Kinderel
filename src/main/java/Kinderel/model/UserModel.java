package Kinderel.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="usersTable")
public class UserModel {

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

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="usersTable_rolesTable",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID")})
    private List<RoleModel> roles = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(String userName, String email, String password, List<RoleModel> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
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

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }
}

