package org.idk.journalApp.service;

import org.bson.types.ObjectId;
import org.idk.journalApp.entity.User;
import org.idk.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public boolean saveNewUser(User user) {
    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRoles(List.of("USER"));
      userRepository.save(user);
      return true;
    } catch (Exception e) {
      log.error("Error saving user", e);
      return false;
    }
  }

  public void saveAdmin(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoles(List.of("USER", "ADMIN"));
    userRepository.save(user);
  }

  public Optional<User> findByid(ObjectId id) {
    return userRepository.findById(id);
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElse(null);
  }

  public void deleteById(ObjectId id) {
    userRepository.deleteById(id);
  }

  public void deleteByUsername(String username) {
    userRepository.deleteByUsername(username);
  }
}
