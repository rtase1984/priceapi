package klagan.priceapi.application;

import klagan.priceapi.entities.PriceEntity;
import klagan.priceapi.service.IPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final IPriceService priceService;

    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> calculatePrice(
            @RequestParam LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) {
        PriceEntity applicablePrice = priceService.findApplicablePrice(brandId, productId, applicationDate);
        Map<String, Object> map = new HashMap<>();
        if (applicablePrice != null) {
            map.put("product", applicablePrice.getProductId());
            map.put("chain", applicablePrice.getBrandId());
            map.put("rate", applicablePrice.getPriceList());
            map.put("starDate", applicablePrice.getStartDate());
            map.put("endDate", applicablePrice.getEndDate());
            map.put("price", applicablePrice.getPrice());
            return ResponseEntity.ok(map);
        }
        return ResponseEntity.noContent().build();
    }

}

