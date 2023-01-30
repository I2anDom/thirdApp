package springBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "coffee_role")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoffeeRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<CoffeeUser> users;

    @Override
    public String getAuthority() {
        return getName();
    }
}
