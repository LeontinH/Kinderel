package Kinderel.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="rolesTable")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesTable_generator")
    @SequenceGenerator(name = "roles_generator", allocationSize = 1)
    @Column(name = "role_id")
    private Long role_id;

    @Column(name = "roleName",nullable=false, unique=true)
    private String roleName;

    public Long getRole_id() {
        return role_id;
    }

    public void setId(Long role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(mappedBy="roles")
    private List<UserModel> users;
}
