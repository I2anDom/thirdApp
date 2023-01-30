package springBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "orderedCoffees")
    private Set<CoffeeOrder> coffeeOrders;
    @NotNull
    private String coffeeName;
    @NotNull
    private float price;
    @NotNull
    private float capacity;
    @NotNull
    private String imageSrc;
}
