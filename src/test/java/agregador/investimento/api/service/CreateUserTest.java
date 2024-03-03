package agregador.investimento.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import agregador.investimento.api.dto.CreateUserDTO;
import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.exceptions.UserAlreadyExists;
import agregador.investimento.api.repository.UserRepository;

@SpringBootTest
class CreateUserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUser createUser;

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should create a user with success")
    void testExecute() {

        var createUserDTO = new CreateUserDTO("John Doe", "john@example.com", "password");
        when(userRepository.findByNameOrEmail(anyString(), anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());

        var createdUser = createUser.execute(createUserDTO);

        assertNotNull(createdUser);
        verify(userRepository, times(1)).findByNameOrEmail("John Doe", "john@example.com");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should throw UserAlreadyExists when trying to create a user with existing name or email")
    void testExecuteUserAlreadyExists() {

        var createUserDTO = new CreateUserDTO("John Doe", "john@example.com", "password");
        when(userRepository.findByNameOrEmail(anyString(), anyString())).thenReturn(Optional.of(new UserEntity()));

        assertThrows(UserAlreadyExists.class, () -> createUser.execute(createUserDTO));
        verify(userRepository, times(1)).findByNameOrEmail("John Doe", "john@example.com");
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
