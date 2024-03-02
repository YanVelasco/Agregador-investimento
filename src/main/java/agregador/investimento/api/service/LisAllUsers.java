package agregador.investimento.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.repository.UserRepository;

@Service
public class LisAllUsers {
     @Autowired
    private UserRepository userRepository;

    public List<UserEntity> execute() {
        return userRepository.findAll();
    }
}
