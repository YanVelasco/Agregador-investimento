package agregador.investimento.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.repository.UserRepository;

@Service
public class ListUserById {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserEntity> execute(UUID userId) {
        return userRepository.findById(userId);
    }
}
