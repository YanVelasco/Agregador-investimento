package agregador.investimento.api.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.dto.CreateAccountDTO;
import agregador.investimento.api.entity.AccountEntity;
import agregador.investimento.api.entity.BillingAdressEntity;
import agregador.investimento.api.repository.AccountRespository;
import agregador.investimento.api.repository.BillingAdressRespository;
import agregador.investimento.api.repository.UserRepository;

@Service
public class CreateAccounte {
    @Autowired
    private AccountRespository accountRespository;
    @Autowired
    private BillingAdressRespository billingAdressRespository;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("null")
    public void execute(String userId, CreateAccountDTO createAccountDTO) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(
                        () -> new NoSuchElementException("Usuário não encontrado"));

        var account = AccountEntity.builder()
                .description(createAccountDTO.description())
                .user(user)
                .billingAdress(null)
                .build();

        var accountCreated = accountRespository.save(account);

        var billingAdress = BillingAdressEntity.builder()
                .id(accountCreated.getId())
                .account(accountCreated)
                .street(createAccountDTO.street())
                .number(createAccountDTO.number())
                .build();

        billingAdressRespository.save(billingAdress);
    }
}
