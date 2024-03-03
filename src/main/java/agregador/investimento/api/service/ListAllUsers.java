package agregador.investimento.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.repository.UserRepository;

@Service
public class ListAllUsers {
    private final UserRepository userRepository;

    public ListAllUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> execute() {
        return userRepository.findAll();
    }
}