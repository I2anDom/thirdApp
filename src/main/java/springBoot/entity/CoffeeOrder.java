package springBoot.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Transactional
public class CoffeeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "coffee_orders_coffees",
            joinColumns = @JoinColumn(name = "coffee_order_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id"))
    private Set<CoffeeEntity> orderedCoffees;
    @Column(columnDefinition = "boolean default true")
    @Getter
    @Setter
    boolean isActive;
    @ManyToOne
    @JoinColumn(name="user_id")
    @Getter
    @Setter
    CoffeeUser coffeeUser;

    public void addCoffeeToOrder(CoffeeEntity coffee){
        orderedCoffees.add(coffee);
    }
}
