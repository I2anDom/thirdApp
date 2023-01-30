package springBoot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springBoot.entity.CoffeeEntity;
import springBoot.entity.CoffeeOrder;
import springBoot.entity.CoffeeRole;
import springBoot.entity.CoffeeUser;
import springBoot.repository.CoffeeRepository;
import springBoot.repository.UserRepository;
import springBoot.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserService userService, CoffeeRepository coffeeRepository){
//        return args -> {
//
//        };
//    }

//    @Bean
//    CommandLineRunner run(UserService userService) {
//        return args -> {
//            userService.saveRole(new CoffeeRole(null,"ROLE_USER", new HashSet<CoffeeUser>()));
//            userService.saveRole(new CoffeeRole(0L,"ROLE_ADMIN", new HashSet<CoffeeUser>()));

//            userService.saveUser(new CoffeeUser(null, "Michael",
//                    "123", new HashSet<CoffeeRole>()));
//            userService.saveUser(new CoffeeUser(null, "Brown",
//                    "qwe", new HashSet<CoffeeRole>()));
//            userService.addRoleToUser("BestArm", "ROLE_ADMIN");
//            userService.addRoleToUser("Anton567", "ROLE_USER");
//        };
//    }

//    @Bean
//    CommandLineRunner run(CoffeeRepository coffeeRepo){
//        return args -> {
//            CoffeeEntity coffee = new CoffeeEntity();
//            coffee.setCoffeeName("Late");
//            coffee.setCapacity(0.5f);
//            coffee.setPrice(50);
//            coffeeRepo.save(coffee);
//        };
//    }

}
