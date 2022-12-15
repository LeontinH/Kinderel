package Kinderel.service;

import Kinderel.model.UserModel;
import Kinderel.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    UserModel user = userRepository.findByUserName(userName);
    if(user != null){
      return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
        user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getRoleName()))
        .collect(Collectors.toList()));
        }else{
          throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

}
