package sectors.sectors.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import sectors.sectors.dto.CreateSectorsDto;
import sectors.sectors.dto.UserSectorDto;
import sectors.sectors.entity.AppUserEntity;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
@Component
public interface AppUserMapper {

    @Mapping(target = "sectors", ignore = true)
    AppUserEntity toEntity(CreateSectorsDto dto);


    @Mapping(target = "sectors", ignore = true)
    UserSectorDto toDto(AppUserEntity entity);
}
