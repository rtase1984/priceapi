package klagan.priceapi.service;

import klagan.priceapi.entities.PriceEntity;
import klagan.priceapi.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService implements IPriceService{

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public PriceEntity findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        LocalDateTime applicationDateEnd = applicationDate.plusSeconds(1); // to include the end date

        List<PriceEntity> applicablePrices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, applicationDate, applicationDate);

        if (!applicablePrices.isEmpty()) {
            return applicablePrices.get(0); // Return the one with the highest priority
        }

        return null; // No applicable price found
    }
}
