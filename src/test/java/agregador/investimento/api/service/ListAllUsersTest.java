package agregador.investimento.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

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
class ListAllUsersTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListAllUsers listAllUsers;
    @Test
    @DisplayName("Should list all users with success")
    void testExecute() {
        // Arrange
        var user1 = new UserEntity();
        var user2 = new UserEntity();
        List<UserEntity> users = Arrays.asList(user1, user2);
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserEntity> result = listAllUsers.execute();

        // Assert
        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no users exist")
    void testExecuteNoUsers() {
        // Arrange
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<UserEntity> result = listAllUsers.execute();

        // Assert
        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findAll();
    }
}
