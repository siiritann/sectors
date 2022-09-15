package sectors.sectors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sectors.sectors.entity.SectorEntity;
import sectors.sectors.repository.SectorRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SectorDataService {

    private final SectorRepository repository;

    public Set<SectorEntity> getByIdIn(List<Long> sectors) {
        return repository.getByIdIn(sectors);
    }

    public List<SectorEntity> getAll() {
        return repository.findAll(Sort.sort(SectorEntity.class).by(SectorEntity::getSorting));
    }

}
