package agregador.investimento.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agregador.investimento.api.dto.AccountWithStockResposeDTO;
import agregador.investimento.api.dto.CreateAccounteWithStockDTO;
import agregador.investimento.api.service.CreateAccounteWithStock;
import agregador.investimento.api.service.ListAllStockByAccount;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private CreateAccounteWithStock createAccounteWithStock;

    @Autowired
    private ListAllStockByAccount listAllStockByAccount;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Object> createAccountWithStock(@PathVariable("accountId") String accountId,
            @RequestBody CreateAccounteWithStockDTO createAccounteWithStockDTO) {
        createAccounteWithStock.execute(accountId, createAccounteWithStockDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountId}/stocks")
    public ResponseEntity<List<AccountWithStockResposeDTO>> createAccountWithStock(@PathVariable("accountId") String accountId) {
        List<AccountWithStockResposeDTO> stocks = listAllStockByAccount.execute(accountId);
        return ResponseEntity.ok(stocks);
    }

}
