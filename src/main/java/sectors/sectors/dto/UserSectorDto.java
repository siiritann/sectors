package sectors.sectors.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSectorDto {

    private Long id;
    private String name;
    private Set<SectorDto> sectors;
    private boolean agreedToTerms;

}
