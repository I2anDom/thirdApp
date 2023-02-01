package springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import springBoot.entity.CoffeeEntity;
import springBoot.entity.CoffeeOrder;
import springBoot.entity.CoffeeOrderCoffeeEntity;
import springBoot.entity.CoffeeUser;
import springBoot.repository.CoffeeRepository;
import springBoot.repository.OrderOrderedCoffeesRepository;
import springBoot.repository.OrderRepository;
import springBoot.service.UserService;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    CoffeeRepository coffeeRepository;
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderOrderedCoffeesRepository orderOrderedCoffeesRepository;
    @GetMapping("/main-page")
    public ModelAndView mainPage(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        CoffeeUser user = userService.getCurrentUser();
        model.addAttribute("user", user);
        model.addAttribute("coffeeList", coffeeRepository.findAll());
//        model.addAttribute("activeOrder", orderRepository.findAllActiveOrders().stream().findFirst());
        return modelAndView;
    }

    @GetMapping("{id}/add-to-order")
    public ModelAndView getEditPage(Model model, @PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        CoffeeEntity coffee = coffeeRepository.findById((long) id).orElse(null);
        ArrayList<CoffeeOrder> ordersList = new ArrayList<>(orderRepository.findAllActiveOrders());
        CoffeeOrder order =  orderRepository.findAllActiveOrders()
                .stream().findFirst()
                .orElse(new CoffeeOrder());
        CoffeeOrderCoffeeEntity coffeeOrderCoffeeEntity = new CoffeeOrderCoffeeEntity();
        coffeeOrderCoffeeEntity.setCoffee(coffee);
        coffeeOrderCoffeeEntity.setOrder(order);
        orderOrderedCoffeesRepository.save(coffeeOrderCoffeeEntity);

//        if(user.getOrder() != null){
//            user.getOrder().addCoffeeToOrder(coffee);
//        }else{
//            Order order = new Order();
//            order.setOrderedCoffees(Collections.singletonList(coffee));
//        }
        modelAndView.setViewName("redirect:/main-page");
        return modelAndView;
    }

    @GetMapping("/order")
    public ModelAndView showOrder(Model model){
//        ArrayList<CoffeeEntity> orderedCoffees = (ArrayList<CoffeeEntity>) user.getOrder().getOrderedCoffees();
        ModelAndView modelAndView = new ModelAndView("orderPage");
        return modelAndView;
    }

    @GetMapping("/add_order")
    public void addOrder(){
        Set<CoffeeEntity> coffeeEntities = new HashSet<>();
        coffeeEntities.add(coffeeRepository.findById(36L).orElse(null));
        CoffeeOrder order = new CoffeeOrder();
        userService.addOrder(order);
    }
}
