package com.nhom9.springjwt.security.services;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhom9.springjwt.models.User;
import com.nhom9.springjwt.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl(user);
  }
  
  private UserDetails UserDetailsImpl(User user) {
	Collection<? extends GrantedAuthority> authorities;
  // TODO Auto-generated method stub
	return new UserDetailsImpl(
		       user.getId(),
		       user.getUsername(),
		       user.getEmail(),
		       user.getPassword(),
		       user.getRole().toString());
}

}

