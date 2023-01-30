package springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBoot.entity.CoffeeRole;

public interface RoleRepository extends JpaRepository<CoffeeRole, Long> {
    CoffeeRole findCoffeeRoleByName(String roleName);
}
