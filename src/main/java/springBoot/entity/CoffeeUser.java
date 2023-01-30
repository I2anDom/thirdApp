package springBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "coffee_user")
@AllArgsConstructor
@NoArgsConstructor
public class CoffeeUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @Size(min = 5, max = 20)
    private String username;
    @Size(min = 5, max = 70)
    private String password;
    @OneToMany(mappedBy = "coffeeUser")
    private Set<CoffeeOrder> orders;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<CoffeeRole> roles;

    public void addOrderToUser(CoffeeOrder order){
//        if(orders == null){
//            orders = new ArrayList<>();
//        }
        orders.add(order);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
