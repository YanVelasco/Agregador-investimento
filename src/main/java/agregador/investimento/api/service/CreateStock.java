package agregador.investimento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agregador.investimento.api.dto.CreateStockDTO;
import agregador.investimento.api.entity.StockEntity;
import agregador.investimento.api.repository.StockRepository;

@Service
public class CreateStock {

    @Autowired
    private StockRepository stockRepository;
    
    @SuppressWarnings("null")
    public StockEntity execute(CreateStockDTO createStockDTO) {
        var stockCreate = StockEntity.builder()
                .id(createStockDTO.stockId())
                .description(createStockDTO.description())
                .build();

        stockRepository.findById(stockCreate.getId()).ifPresent(existingStock -> {
            throw new IllegalArgumentException("Stock already exists");
        });

        return stockRepository.save(stockCreate);
    }

}
