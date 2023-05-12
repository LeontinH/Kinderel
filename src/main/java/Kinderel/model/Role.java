package Kinderel.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="rolesTable")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesTable_generator")
    @SequenceGenerator(name = "roles_generator", allocationSize = 1)
    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "roleName",nullable=false, unique=true)
    private String roleName;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "role")
    private List<User> user;

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
