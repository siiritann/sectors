package sectors.sectors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sectors.sectors.entity.AppUserEntity;
import sectors.sectors.repository.AppUserRepository;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final AppUserRepository repository;

    public AppUserEntity save(AppUserEntity entity) {
        return repository.save(entity);
    }

    public AppUserEntity getById(Long id) {
        return repository.findById(id).orElse(null);
    }

}
