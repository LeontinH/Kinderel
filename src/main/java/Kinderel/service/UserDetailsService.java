package Kinderel.service;

import Kinderel.model.User;
import Kinderel.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    org.springframework.security.core.userdetails.User.UserBuilder builder = null;
    User user = userRepository.findByUserName(userName);
    if (user==null) {
      throw new UsernameNotFoundException(userName);
    }else{
      builder = org.springframework.security.core.userdetails.User.withUsername(userName);
      builder.password(user.getPassword());
      builder.roles(String.valueOf(user.getRole().getRoleName()));
    }
    return builder==null ? null : builder.build();
  }

}
