package org.idk.journalApp.service;

import org.idk.journalApp.entity.User;
import org.idk.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class UserServiceTests {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserService userService;

  @Disabled
  @ParameterizedTest
  @CsvSource({
          "1,1,2",
          "2,3,5",
  })
  public void test(int a, int b, int expected) {
    assertEquals(expected, a + b);
  }

  @Disabled
  @ParameterizedTest
  @ValueSource(strings = {"first"})
  public void testFindByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      fail("User not found: " + username);
    }
  }

  @Disabled
  @ParameterizedTest
  @ArgumentsSource(UserArgumentsProvider.class)
  public void testSaveNewUser(User user) {
    boolean saved = userService.saveNewUser(user);
    assertTrue(saved);
  }
}
