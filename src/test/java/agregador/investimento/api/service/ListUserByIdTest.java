package agregador.investimento.api.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import agregador.investimento.api.repository.UserRepository;

public class ListUserByIdTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListUserById listUserById;

    @Test
    void testExecute() {

    }
}
