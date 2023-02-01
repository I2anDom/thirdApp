package springBoot.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Entity
public class CoffeeOrder {
    private Long id;

    boolean isActive;

    private CoffeeUser coffeeUser;

    private Set<CoffeeOrderCoffeeEntity> coffeesOrders = new HashSet<CoffeeOrderCoffeeEntity>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setActive(boolean isActive){
        this.isActive = isActive;
    }

    @Column(columnDefinition = "boolean default true")
    public boolean isActive(){
        return isActive;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public CoffeeUser getCoffeeUser(){
        return coffeeUser;
    }

    public void setCoffeeUser(CoffeeUser coffeeUser){
        this.coffeeUser = coffeeUser;
    }

    @OneToMany(mappedBy = "order")
    public Set<CoffeeOrderCoffeeEntity> getCoffeesOrders(){
        return coffeesOrders;
    }

    public void setCoffeesOrders(Set<CoffeeOrderCoffeeEntity> coffeesOrders){
        this.coffeesOrders = coffeesOrders;
    }

    public void addCoffeesOrder(CoffeeOrderCoffeeEntity coffeesOrder){
        coffeesOrders.add(coffeesOrder);
    }

//    public void addCoffeeToOrder(CoffeeEntity coffee){
//        coffeesOrders.add(coffee);
//    }
}
