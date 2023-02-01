package springBoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CoffeeEntity {
    private Long id;
    @NotNull
    private String coffeeName;
    @NotNull
    private float price;
    @NotNull
    private float capacity;
    @NotNull
    private String imageSrc;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public float getPrice() {
        return price;
    }

    public float getCapacity() {
        return capacity;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    private Set<CoffeeOrderCoffeeEntity> coffeesOrders = new HashSet<CoffeeOrderCoffeeEntity>();

    @OneToMany(mappedBy = "coffee")
    public Set<CoffeeOrderCoffeeEntity> getCoffeesOrders() {
        return coffeesOrders;
    }

    public void setCoffeesOrders(Set<CoffeeOrderCoffeeEntity> coffeesOrders){
        this.coffeesOrders = coffeesOrders;
    }

    public void addCoffeeOrder(CoffeeOrderCoffeeEntity coffeeOrder){
        coffeesOrders.add(coffeeOrder);
    }
}
