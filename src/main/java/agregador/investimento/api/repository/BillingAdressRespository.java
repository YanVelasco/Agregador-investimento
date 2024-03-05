package agregador.investimento.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import agregador.investimento.api.entity.BillingAdressEntity;

public interface BillingAdressRespository extends JpaRepository<BillingAdressEntity, UUID>{
    
}
