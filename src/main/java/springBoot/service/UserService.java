package springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springBoot.entity.CoffeeOrder;
import springBoot.entity.CoffeeRole;
import springBoot.entity.CoffeeUser;
import springBoot.repository.RoleRepository;
import springBoot.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CoffeeUser user = userRepository.findCoffeeUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean saveUser(CoffeeUser user){
        CoffeeUser userFromDB = userRepository.findCoffeeUserByUsername(user.getUsername());
        if(userFromDB != null){
            return false;
        }
        user.setRoles(Collections.singleton(roleRepository.findCoffeeRoleByName("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void saveRole(CoffeeRole role){
        roleRepository.save(role);
    }

    public void addRoleToUser(String username, String roleName){
        CoffeeUser user = (CoffeeUser)(loadUserByUsername(username));
        user.getRoles().add(roleRepository.findCoffeeRoleByName(roleName));
        userRepository.save(user);
    }

    public void addOrder(CoffeeOrder order){
        CoffeeUser user = getCurrentUser();
        user.addOrderToUser(order);
        userRepository.save(user);
    }

    public CoffeeUser getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CoffeeUser user = (CoffeeUser)(auth.getPrincipal());
        return user;
    }
}
