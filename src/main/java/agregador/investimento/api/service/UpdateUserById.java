package agregador.investimento.api.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.dto.UpdateUserDTO;
import agregador.investimento.api.entity.UserEntity;
import agregador.investimento.api.repository.UserRepository;

@Service
public class UpdateUserById {
    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("null")
    public UserEntity execute(String userId, UpdateUserDTO updateUserDTO) {
        var id = UUID.fromString(userId);

        // Verifica se o usuário existe antes de atualizá-lo
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        // Atualiza os campos do usuário com base nos dados do DTO, apenas se não forem
        // nulos
        if (updateUserDTO.name() != null) {
            user.setName(updateUserDTO.name());
        }
        if (updateUserDTO.password() != null) {
            user.setPassword(updateUserDTO.password());
        }

        // Salva as alterações no repositório
        return userRepository.save(user);
    }
}
