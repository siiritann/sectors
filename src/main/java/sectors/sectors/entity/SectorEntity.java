package sectors.sectors.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
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
    private Long parentId;
    private String name;
    // TODO remove children after refactoring to composing children in service
    @OneToMany(mappedBy = "parentId")
    @OrderBy("name")
    private List<SectorEntity> children;
    @ManyToMany(mappedBy = "sectors")
    private Set<AppUserEntity> appUsers;


}
