package agregador.investimento.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import agregador.investimento.api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class DeleteUserByIdTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserById deleteUserById;

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should delete a user with success")
    void testExecute() {
        // Arrange
        String userId = UUID.randomUUID().toString();
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.when(userRepository.existsById(uuidCaptor.capture())).thenReturn(true);

        // Act
        deleteUserById.execute(userId);

        // Assert
        UUID capturedUuid = uuidCaptor.getValue();
        verify(userRepository, times(1)).existsById(capturedUuid);
        verify(userRepository, times(1)).deleteById(capturedUuid);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("Should throw NoSuchElementException when trying to delete a non-existing user")
    void testExecuteUserDoesNotExist() {
        // Arrange
        String userId = UUID.randomUUID().toString();
        when(userRepository.existsById(any(UUID.class))).thenReturn(false);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> deleteUserById.execute(userId));
        verify(userRepository, times(1)).existsById(any(UUID.class));
        verify(userRepository, never()).deleteById(any(UUID.class));
    }
}
