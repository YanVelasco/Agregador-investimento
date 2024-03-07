package agregador.investimento.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import agregador.investimento.api.dto.CreateStockDTO;
import agregador.investimento.api.service.CreateStock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private CreateStock createStock;

    @PostMapping()
    public ResponseEntity<CreateStockDTO> createStock(@RequestBody CreateStockDTO createStockDTO,
            UriComponentsBuilder uriComponentsBuilder) {
        var stock = createStock.execute(createStockDTO);
        var uri = uriComponentsBuilder.path("/stocks/{id}").buildAndExpand(stock.getId()).toUri();
        return ResponseEntity.created(uri).body(createStockDTO);
    }

}
