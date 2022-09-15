package sectors.sectors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sectors.sectors.entity.SectorEntity;

import java.util.List;
import java.util.Set;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Long> {

    Set<SectorEntity> getByIdIn(List<Long> ids);

}


