package agregador.investimento.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agregador.investimento.api.dto.CreateAccounteWithStockDTO;
import agregador.investimento.api.service.CreateAccounteWithStock;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private CreateAccounteWithStock createAccounteWithStock;

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<Object> createAccountWithStock(@PathVariable("accountId") String accountId,
            @RequestBody CreateAccounteWithStockDTO createAccounteWithStockDTO) {
        createAccounteWithStock.execute(accountId, createAccounteWithStockDTO);
        return ResponseEntity.ok().build();
    }

}
