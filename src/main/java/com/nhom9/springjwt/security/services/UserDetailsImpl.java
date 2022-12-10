package com.nhom9.springjwt.security.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhom9.springjwt.models.User;

public class UserDetailsImpl implements UserDetails {
 private static final long serialVersionUID = 1L;

 private Long id;

 private String username;

 private String email;

 @JsonIgnore
 private String password;

 private Collection<? extends GrantedAuthority> authorities;

 private String role;

  public UserDetailsImpl(Long id, String username, String email, String password, List<GrantedAuthority> list,
      Collection<? extends GrantedAuthority> authorities, String roleString) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
    this.role = roleString;
  }

  public UserDetailsImpl(Long id, String username, String email, String password, String role) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
//	this.authorities = ;
	this.role = role;
}

//public static UserDetailsImpl build(User user) {
//     List<GrantedAuthority> authorities = user;
//    
//     
//    return new UserDetailsImpl(
//        user.getId(),
//        user.getUsername(),
//        user.getEmail(),
//        user.getPassword(),
//        user.getRole(),
//        authorities);
//
//  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  
  }

public String getRole() {
    return role;
}
}