package agregador.investimento.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class ListUserByIdTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListUserById listUserById;

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should return user when user exists")
    void testExecuteUserExists() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<UserEntity> result = listUserById.execute(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        verify(userRepository, times(1)).findById(userId);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should return empty when user does not exist")
    void testExecuteUserDoesNotExist() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserEntity> result = listUserById.execute(userId);

        // Assert
        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }
}