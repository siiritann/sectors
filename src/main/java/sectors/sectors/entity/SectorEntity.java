package sectors.sectors.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sector")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private Integer level;
    private Integer sorting;
    @ManyToMany(mappedBy = "sectors")
    private Set<AppUserEntity> appUsers;


}
