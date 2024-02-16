package iss.sa.team2.ad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import iss.sa.team2.ad.enums.Gender;
import iss.sa.team2.ad.enums.MyType;
import iss.sa.team2.ad.enums.UserPosition;
import iss.sa.team2.ad.enums.UserStatus;
import iss.sa.team2.ad.interfacemethods.IRegularUserAnimeService;
import iss.sa.team2.ad.interfacemethods.IUserService;
import iss.sa.team2.ad.model.RegularUser;
import iss.sa.team2.ad.model.RegularUserAnime;
import iss.sa.team2.ad.model.User;
import iss.sa.team2.ad.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable String id, Model model) {
        Optional<User> userOptional = userService.getUserById(id);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return "user"; // 返回视图名称，比如"user.html"
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        return "users"; // 返回视图名称，比如"users.html"
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/animes/animelist";
    }
    
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null && !userId.isEmpty()) {
            return "redirect:/user/homePage";
        } else {
            return "login";
        }
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String account,
                               @RequestParam("password") String password,
                               HttpSession session, Model model) {
        String userId = userService.findUserIdByAccountAndPassword(account, password);

        if (userId==null) {
        	model.addAttribute("loginError", "Invalid account or password"); 
            return "login";     
        } else {
        	session.setAttribute("userId", userId); 
            return "redirect:/user/homePage";
        }
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    	model.addAttribute("GENDER_ENUM", Gender.values());
        model.addAttribute("STATUS_ENUM", UserStatus.values());
    	model.addAttribute("regularUser", new RegularUser());
        return "register"; 
    }
    
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("regularUser") RegularUser regularUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("GENDER_ENUM", Gender.values());
            model.addAttribute("STATUS_ENUM", UserStatus.values());
            model.addAttribute("regularUser", regularUser);
            return "register";
        }

        String userId = UUID.randomUUID().toString();

        regularUser.setId(userId); 
        regularUser.setPosition(UserPosition.RegularUser);
        regularUser.setStatus(UserStatus.NORMAL);
        
        userService.saveUser(regularUser);

        return "redirect:/user/login"; 
    }
    
    @GetMapping("/homePage")
    public String getUserDetails(Model model,HttpSession session) {
    	String userId = (String) session.getAttribute("userId");
    	Optional<User> userOptional = userService.getUserById(userId);
    	
    	if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            model.addAttribute("user", user);
            return "homePage"; 
        } else {
            
            return "error"; 
        }
    }
   
}
