package agregador.investimento.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.repository.UserRepository;

@Service
public class DeleteUserById {
    @Autowired
    private UserRepository userRepository;

    public void execute(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);
        if (userExists) {
             userRepository.deleteById(id);
        }
    }
}
