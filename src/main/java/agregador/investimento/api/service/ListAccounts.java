package agregador.investimento.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.dto.AccountResponseDTO;
import agregador.investimento.api.repository.UserRepository;

@Service
public class ListAccounts {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("null")
    public List<AccountResponseDTO> execute(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Usuário não encontrado"));

        return user.getAccounts()
                .stream()
                .map(account -> new AccountResponseDTO(account.getId().toString(), account.getDescription()))
                .toList();
    }
}
