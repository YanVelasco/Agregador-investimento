package agregador.investimento.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import agregador.investimento.api.entity.AccountEntity;

public interface AccountRespository extends JpaRepository<AccountEntity, UUID>{
}