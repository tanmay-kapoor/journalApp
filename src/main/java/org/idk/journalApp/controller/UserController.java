package org.idk.journalApp.controller;

import org.idk.journalApp.entity.User;
import org.idk.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PutMapping("/{username}")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    User userInDb = userService.findByUsername(username);

    userInDb.setUsername(user.getUsername());
    userInDb.setPassword(user.getPassword());
    userService.saveNewUser(userInDb);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping
  public ResponseEntity<?> deleteUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    userService.deleteByUsername(username);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}


