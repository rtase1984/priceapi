package klagan.priceapi.infrastructure.web;

import klagan.priceapi.application.PriceServicePort;
import klagan.priceapi.infrastructure.dto.PriceResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final PriceServicePort priceService;
    public PriceController(PriceServicePort priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<PriceResponseDTO> calculatePrice(
            @RequestParam LocalDateTime applicationDate,
            @RequestParam Long productId,
            @RequestParam Long brandId
    ) throws Exception {
            return ResponseEntity.ok(priceService.findApplicablePrice(brandId, productId, applicationDate));
    }



}

