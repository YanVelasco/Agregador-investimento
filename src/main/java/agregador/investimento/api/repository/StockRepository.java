package agregador.investimento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agregador.investimento.api.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, String>{
}
