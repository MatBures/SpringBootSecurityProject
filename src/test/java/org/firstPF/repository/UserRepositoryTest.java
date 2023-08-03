package org.firstPF.repository;

import org.firstPF.user.User;
import org.firstPF.user.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final String email = "test@example.com";
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setEmail(email);
        user.setFirstName("Ludvik");
        user.setLastName("Bříza");
        user.setRole(Role.USER);

        userRepository.save(user);
    }
    @AfterEach
    public void afterEach() {
        userRepository.delete(user);
    }

    @Test
    public void testFindByEmail_ExistingEmail_ShouldReturnUser() {
        Optional<User> foundUser = userRepository.findByEmail(email);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo(email);
    }

    @Test
    public void testFindByEmail_NonExistingEmail_ShouldReturnEmptyOptional() {
        String nonExistingEmail = "nonexisting@example.com";

        Optional<User> foundUser = userRepository.findByEmail(nonExistingEmail);

        assertThat(foundUser).isEmpty();
    }
}
