package springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBoot.entity.CoffeeUser;

public interface UserRepository extends JpaRepository<CoffeeUser, Long> {
    CoffeeUser findCoffeeUserByUsername(String username);
}
