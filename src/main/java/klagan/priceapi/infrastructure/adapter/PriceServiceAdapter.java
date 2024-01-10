package klagan.priceapi.infrastructure.adapter;

import klagan.priceapi.application.PriceServicePort;
import klagan.priceapi.infrastructure.dto.PriceResponseDTO;
import klagan.priceapi.infrastructure.exception.PriceException;
import klagan.priceapi.infrastructure.mapper.PriceMapper;
import klagan.priceapi.infrastructure.persistence.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class PriceServiceAdapter implements PriceServicePort {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceResponseDTO findApplicablePrice(Long brandId, Long productId, LocalDateTime applicationDate) {

        return PriceMapper.INSTANCE.toPriceResponseDTO(priceRepository.findFirstByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
                        applicationDate, applicationDate, productId, brandId)
                .orElseThrow(() -> new PriceException(HttpStatus.NO_CONTENT, "Not Value")));
    }
}
