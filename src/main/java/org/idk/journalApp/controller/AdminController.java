package org.idk.journalApp.controller;

import org.idk.journalApp.entity.User;
import org.idk.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private UserService userService;

  @GetMapping("/all-users")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> allUsers = userService.getAll();

    if (allUsers == null || allUsers.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(allUsers, HttpStatus.OK);
  }

  @PostMapping("/create-admin-user")
  public ResponseEntity<User> createUser(User user) {
    userService.saveAdmin(user);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }
}
