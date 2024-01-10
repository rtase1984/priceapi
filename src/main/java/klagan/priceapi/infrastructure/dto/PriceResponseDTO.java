package klagan.priceapi.infrastructure.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PriceResponseDTO {
    private Long productId;
    private Long chain;
    private Long rate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal finalPrice;

}
