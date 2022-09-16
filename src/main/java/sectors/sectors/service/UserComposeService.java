
package sectors.sectors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sectors.sectors.dto.CreateUserSectorsDto;
import sectors.sectors.mapper.AppUserMapper;
import sectors.sectors.dto.UserSectorDto;
import sectors.sectors.entity.AppUserEntity;

@Service
@RequiredArgsConstructor
public class UserComposeService {

    private final AppUserMapper mapper;

    public AppUserEntity composeEntity(CreateUserSectorsDto dto) {
        return mapper.toEntity(dto);
    }

    public UserSectorDto composeDto(AppUserEntity entity) {
        return mapper.toDto(entity);
    }

}
