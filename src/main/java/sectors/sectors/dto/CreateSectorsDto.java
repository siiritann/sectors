package sectors.sectors.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSectorsDto {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private List<Long> sectors;
    private boolean agreedToTerms;

}
