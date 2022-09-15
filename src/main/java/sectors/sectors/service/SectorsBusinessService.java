package sectors.sectors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sectors.sectors.dto.CreateSectorsDto;
import sectors.sectors.dto.SectorDto;
import sectors.sectors.dto.UserSectorDto;
import sectors.sectors.entity.AppUserEntity;
import sectors.sectors.entity.SectorEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Validated
public class SectorsBusinessService {

    private final SectorDataService sectorDataService;
    private final UserDataService userDataService;
    private final SectorComposeService sectorComposeService;
    private final UserComposeService userComposeService;

    public List<SectorDto> getAllSectors() {
        List<SectorEntity> entities = sectorDataService.getAll();
        return sectorComposeService.composeDtoList(entities);
    }

    @Transactional
    public UserSectorDto create(@Valid CreateSectorsDto dto) {
        AppUserEntity entity = userComposeService.composeEntity(dto);

        Set<SectorEntity> sectors = sectorDataService.getByIdIn(dto.getSectors());
        entity.setSectors(sectors);
        entity = userDataService.save(entity);

        return userComposeService.composeDto(entity);
    }

    public UserSectorDto getById(Long id) {
        AppUserEntity entity = userDataService.getById(id);
        if (entity == null) {
            return null;
        }
        UserSectorDto dto =  userComposeService.composeDto(entity);
        Set<SectorEntity> sectors = entity.getSectors();
        dto.setSectors(sectorComposeService.composeSet(sectors));

        return dto;
    }

}
