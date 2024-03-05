package agregador.investimento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agregador.investimento.api.entity.AccountStockEntity;
import agregador.investimento.api.entity.AccountStockId;

@Repository
public interface AccountStockRespository extends JpaRepository<AccountStockEntity, AccountStockId>{
}
