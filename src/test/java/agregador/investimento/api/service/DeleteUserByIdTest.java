package agregador.investimento.api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import agregador.investimento.api.repository.UserRepository;

public class DeleteUserByIdTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DeleteUserById deleteUserById;

    @Test
    void testExecute() {

    }
}
