package agregador.investimento.api.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.dto.CreateAccounteWithStockDTO;
import agregador.investimento.api.entity.AccountStockEntity;
import agregador.investimento.api.entity.AccountStockId;
import agregador.investimento.api.repository.AccountRespository;
import agregador.investimento.api.repository.AccountStockRespository;
import agregador.investimento.api.repository.StockRepository;

@Service
public class CreateAccounteWithStock {

    @Autowired
    private AccountRespository accountRespository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private AccountStockRespository accountStockRespository;

    @SuppressWarnings("null")
    public void execute(String accountId, CreateAccounteWithStockDTO createAccounteWithStockDTO) {

        var account = accountRespository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        var stock = stockRepository.findById(createAccounteWithStockDTO.stockId())
                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));

        var accountWithStockId = AccountStockId.builder()
                .accountId(account.getId())
                .stockId(stock.getId())
                .build();

        var entity = AccountStockEntity.builder()
                .id(accountWithStockId)
                .account(account)
                .stock(stock)
                .quantity(createAccounteWithStockDTO.quantity())
                .build();

        accountStockRespository.save(entity);
    }

}
