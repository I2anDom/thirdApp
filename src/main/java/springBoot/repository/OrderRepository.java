package springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springBoot.entity.CoffeeOrder;
import springBoot.entity.CoffeeUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

public interface OrderRepository extends JpaRepository<CoffeeOrder, Long> {
    @Query(
            value = "SELECT u FROM CoffeeOrder u WHERE u.active = true")
    Collection<CoffeeOrder> findAllActiveOrders();
}
