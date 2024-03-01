package agregador.investimento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.dto.CreateUserDTO;
import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.exceptions.UserAlreadyExists;
import agregador.investimento.api.repository.UserRepository;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(CreateUserDTO createUserDTO) {
        var user = UserEntity.builder()
                .name(createUserDTO.name())
                .email(createUserDTO.email())
                .password(createUserDTO.password())
                .build();
        
        userRepository.findByNameOrEmail(user.getName(), user.getEmail()).ifPresent(existingUser -> {
            throw new UserAlreadyExists("Usuário já possúi um cadastro");
        });

        return userRepository.save(user);
    }
}
