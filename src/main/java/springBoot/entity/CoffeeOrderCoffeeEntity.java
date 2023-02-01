package springBoot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "order_ordered_coffees")
public class CoffeeOrderCoffeeEntity {
    private long id;
    private CoffeeOrder order;
    private CoffeeEntity coffee;


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    public CoffeeOrder getOrder(){
        return order;
    }

    public void setOrder(CoffeeOrder order){
        this.order = order;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coffee_id")
    public CoffeeEntity getCoffee(){
        return coffee;
    }

    public void setCoffee(CoffeeEntity coffee){
        this.coffee = coffee;
    }
}
