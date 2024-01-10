package klagan.priceapi.application;

import klagan.priceapi.domain.entities.PriceEntity;
import klagan.priceapi.infrastructure.dto.PriceResponseDTO;

import java.time.LocalDateTime;

public interface PriceServicePort {

    PriceResponseDTO findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) throws Exception;

}
