package klagan.priceapi.infrastructure.mapper;

import klagan.priceapi.domain.entities.PriceEntity;
import klagan.priceapi.infrastructure.dto.PriceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "priceListId", target = "rate")
    @Mapping(source = "brandId", target = "chain")
    PriceResponseDTO toPriceResponseDTO(PriceEntity priceEntity);

}
