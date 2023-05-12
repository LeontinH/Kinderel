package Kinderel.service;

import Kinderel.model.DTO;
import Kinderel.model.Role;
import Kinderel.model.User;
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

    public void saveUser(DTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        Role role = roleRepository.findByRoleName("USER");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRole(role);
        userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<DTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private DTO convertEntityToDto(User user){
        DTO dto = new DTO();
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        return dto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setRoleName("USER");
        return roleRepository.save(role);
    }

}