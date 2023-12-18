package klagan.priceapi.service;

import klagan.priceapi.entities.PriceEntity;

import java.time.LocalDateTime;

public interface IPriceService {

    PriceEntity findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate);

}
