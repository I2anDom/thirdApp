package springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springBoot.entity.CoffeeUser;
import springBoot.repository.UserRepository;
import springBoot.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new CoffeeUser());
        return "registration";
    }

    @PostMapping("/registration")
    public String confirmRegistration(@Valid @ModelAttribute CoffeeUser user, final BindingResult result,
                                      Model model){
        List<String> errors;
        if(result.hasErrors()){
            model.addAttribute("user", user);
            List<FieldError> fieldErrors = result.getFieldErrors();
            errors = fieldErrors.stream()
                    .map(i -> i.getField() + " " + i.getDefaultMessage())
                    .toList();
            model.addAttribute("errorsList", errors);
            return "registration";
        }

        if(userService.saveUser(user)){
            return "login";
        }else{
            model.addAttribute("user", new CoffeeUser());
            errors = new ArrayList<>();
            errors.add("Account with this username already exists");
            model.addAttribute("errorsList", errors);
            return "registration";
        }
    }
}
