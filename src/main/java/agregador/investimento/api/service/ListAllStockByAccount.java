package agregador.investimento.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.dto.AccountWithStockResposeDTO;
import agregador.investimento.api.repository.AccountRespository;

@Service
public class ListAllStockByAccount {

    @Autowired
    private AccountRespository accountRespository;

    public List<AccountWithStockResposeDTO> execute(String accountId) {
        @SuppressWarnings("null")
        var account = accountRespository.findById(UUID.fromString(accountId))
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return account.getStocks().stream()
                .map(stock -> new AccountWithStockResposeDTO(stock.getStock()
                        .getId(),
                        stock
                                .getQuantity(),
                        00))
                .toList();
    }
}
