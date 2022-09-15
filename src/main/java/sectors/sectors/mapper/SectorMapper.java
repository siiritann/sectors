package sectors.sectors.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import sectors.sectors.dto.SectorDto;
import sectors.sectors.entity.SectorEntity;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
@Component
public interface SectorMapper {

    SectorDto toDto(SectorEntity entity);

}
