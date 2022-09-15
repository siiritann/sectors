
package sectors.sectors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sectors.sectors.mapper.SectorMapper;
import sectors.sectors.dto.SectorDto;
import sectors.sectors.entity.SectorEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorComposeService {

    private final SectorMapper mapper;

    public List<SectorDto> composeDtoList(List<SectorEntity> entities) {
        return entities.stream().map(this::composeDto).toList();
    }

    private SectorDto composeDto(SectorEntity entity) {
        return mapper.toDto(entity);
    }

    public Set<SectorDto> composeSet(Set<SectorEntity> entities) {
        return entities.stream().map(this::composeDto).collect(Collectors.toSet());
    }

}
