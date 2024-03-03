package agregador.investimento.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import agregador.investimento.api.dto.UpdateUserDTO;
import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UpdateUserByIdTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserById updateUserById;

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should update user when user exists")
    void testExecuteUserExists() {
        // Arrange
        String userId = UUID.randomUUID().toString();
        UserEntity user = new UserEntity();
        UpdateUserDTO updateUserDTO = new UpdateUserDTO("newName", "newPassword");
        Mockito.when(userRepository.findById(UUID.fromString(userId))).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(any(UserEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        UserEntity result = updateUserById.execute(userId, updateUserDTO);

        // Assert
        assertEquals("newName", result.getName());
        assertEquals("newPassword", result.getPassword());
        verify(userRepository, times(1)).findById(UUID.fromString(userId));
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should throw exception when user does not exist")
    void testExecuteUserDoesNotExist() {
        // Arrange
        String userId = UUID.randomUUID().toString();
        UpdateUserDTO updateUserDTO = new UpdateUserDTO("newName", "newPassword");
        Mockito.when(userRepository.findById(UUID.fromString(userId))).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> updateUserById.execute(userId, updateUserDTO));
        verify(userRepository, times(1)).findById(UUID.fromString(userId));
    }
}