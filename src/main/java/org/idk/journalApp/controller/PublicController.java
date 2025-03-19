package org.idk.journalApp.controller;

import org.idk.journalApp.entity.User;
import org.idk.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

  @Autowired
  private UserService userService;

  @PostMapping("/create-user")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    try {
      userService.saveNewUser(user);
      return new ResponseEntity<>(user, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/health")
  public String healthCheck() {
    return "health check";
  }
}
