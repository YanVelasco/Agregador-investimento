package agregador.investimento.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import agregador.investimento.api.client.BrapiClient;
import agregador.investimento.api.dto.AccountWithStockResposeDTO;
import agregador.investimento.api.repository.AccountRespository;

@Service
public class ListAllStockByAccount {

        @Value("${BRAPI_TOKEN}")
        private String TOKEN;

        @Autowired
        private AccountRespository accountRespository;

        @Autowired
        private BrapiClient brapiClient;

        public List<AccountWithStockResposeDTO> execute(String accountId) {
                @SuppressWarnings("null")
                var account = accountRespository.findById(UUID.fromString(accountId))
                                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

                return account.getStocks()
                                .stream()
                                .map(stock -> new AccountWithStockResposeDTO(
                                                stock.getStock().getId(),
                                                stock.getQuantity(),
                                                getTotal(
                                                                stock.getQuantity(),
                                                                stock.getStock().getId()

                                                )))
                                .toList();
        }

        private double getTotal(Integer quantity, String id) {
                var response = brapiClient.getQuote(TOKEN, id);
                var price = response.results().getFirst().regularMarketPrice();
                return price * quantity;
        }
}
