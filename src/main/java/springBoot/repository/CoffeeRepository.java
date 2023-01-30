package springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBoot.entity.CoffeeEntity;

public interface CoffeeRepository extends JpaRepository<CoffeeEntity, Long> {
}
