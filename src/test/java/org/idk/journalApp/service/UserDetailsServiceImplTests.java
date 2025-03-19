package org.idk.journalApp.service;

import org.idk.journalApp.entity.User;
import org.idk.journalApp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

  @InjectMocks
  private UserDetailsServiceImpl userDetailsService;

  @Mock
  private UserRepository userRepository;

  private AutoCloseable autoCloseable;

  @BeforeEach
  public void setUp() {
    autoCloseable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  public void tearDown() throws Exception {
    autoCloseable.close();
  }

  @Disabled
  @Test
  public void testLoadUserByUsername() {
    when(userRepository.findByUsername(ArgumentMatchers.anyString()))
            .thenReturn(
                    Optional.of(User.builder()
                            .username("idk")
                            .password("password")
                            .roles(List.of("USER"))
                            .build())
            );

    UserDetails user = userDetailsService.loadUserByUsername("idk");
    assertNotNull(user);
  }
}
