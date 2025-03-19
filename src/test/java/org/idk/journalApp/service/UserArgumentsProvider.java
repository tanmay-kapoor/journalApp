package org.idk.journalApp.service;

import org.idk.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
    return Stream.of(
            Arguments.of(User.builder().username("test1").password("test1").build()),
            Arguments.of(User.builder().username("test2").password("").build())
    );
  }
}
