package partizan.global.controller;

import partizan.global.model.Data;
import partizan.global.model.User;
import partizan.global.service.UserService;
import partizan.global.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    private static final String REDIRECT = "redirect:/";
    private static final String PHONE_BOOK = "phone-book";
    private static final String STATUS = "status";
    private static final String USER = "user";
    private static final String USERS = "users";
    private static final String ADD_USER = "add-user";


    @Autowired
    private UserService service;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/")
    public String getUsers(ModelMap model, @ModelAttribute("status") String status) {
        Data inputData = service.getAllUsers();
        model.addAttribute(USERS, inputData.getUsers());
        model.addAttribute(STATUS, status);
        return PHONE_BOOK;
    }

    @GetMapping("/add-user")
    public String addUser(ModelMap model) {
        model.addAttribute(USER, new User());
        return ADD_USER;
    }

    @PostMapping("/add-user")
    public String addUser(User user, BindingResult result, RedirectAttributes redirect) {
        userValidator.validate(user, result);
        if (result.hasErrors())
            return ADD_USER;
        Data inputData = service.saveUser(user);
        redirect.addFlashAttribute(STATUS, inputData.getStatus());
        return REDIRECT;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirect) {
        Data inputData = service.deleteUserById(id);
        redirect.addFlashAttribute(STATUS, inputData.getStatus());
        return REDIRECT;
    }
}
