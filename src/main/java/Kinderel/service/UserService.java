package Kinderel.service;

import Kinderel.model.DTOModel;
import Kinderel.model.RoleModel;
import Kinderel.model.UserModel;
import Kinderel.repository.RoleRepository;
import Kinderel.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(DTOModel dtoModel) {
        UserModel user = new UserModel();
        user.setUserName(dtoModel.getUserName());
        user.setEmail(dtoModel.getEmail());

        user.setPassword(passwordEncoder.encode(dtoModel.getPassword()));
        RoleModel role = roleRepository.findByRoleName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    public UserModel findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<DTOModel> findAllUsers() {
        List<UserModel> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private DTOModel convertEntityToDto(UserModel user){
        DTOModel dtoModel = new DTOModel();
        dtoModel.setUserName(user.getUserName());
        dtoModel.setEmail(user.getEmail());
        return dtoModel;
    }

    private RoleModel checkRoleExist() {
        RoleModel role = new RoleModel();
        role.setRoleName("ROLE_USER");
        return roleRepository.save(role);
    }

}