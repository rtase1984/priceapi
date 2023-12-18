package klagan.priceapi.repository;

import klagan.priceapi.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDateEnd
    );

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime applicationDate, LocalDateTime applicationDateEnd
    );
}
