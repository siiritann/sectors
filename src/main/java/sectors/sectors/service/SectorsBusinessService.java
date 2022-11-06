package sectors.sectors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import sectors.sectors.dto.CreateUserSectorsDto;
import sectors.sectors.dto.SectorDto;
import sectors.sectors.dto.UserSectorDto;
import sectors.sectors.entity.AppUserEntity;
import sectors.sectors.entity.SectorEntity;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.util.CollectionUtils.isEmpty;

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
    public UserSectorDto create(@Valid CreateUserSectorsDto dto) {
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
        UserSectorDto dto = userComposeService.composeDto(entity);
        Set<SectorEntity> sectors = entity.getSectors();
        dto.setSectors(sectorComposeService.composeSet(sectors));

        return dto;
    }

    public LinkedHashMap<Long, String> getSectorsMap() {
        List<SectorEntity> sectorEntities = sectorDataService.getTopLevelSectors();
        List<SectorDto> dtoList = sectorComposeService.composeDtoList(sectorEntities);
        LinkedHashMap<Long, String> modifiedSectors = new LinkedHashMap<>();
        addSectors(0, dtoList, modifiedSectors);
        return modifiedSectors;
    }

    private void addSectors(int level, List<SectorDto> sectors, Map<Long, String> modifiedSectors) {
        for (SectorDto sector : sectors) {
            modifiedSectors.put(sector.getId(), "&nbsp;".repeat(level * 4) + sector.getName());
            if (!isEmpty(sector.getChildren())) {
                addSectors(level + 1, sector.getChildren(), modifiedSectors);
            }
        }
    }

}
