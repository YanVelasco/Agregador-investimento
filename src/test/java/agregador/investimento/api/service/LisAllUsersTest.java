package agregador.investimento.api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import agregador.investimento.api.repository.UserRepository;

public class LisAllUsersTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LisAllUsers lisAllUsers;

    @Test
    void testExecute() {

    }
}
