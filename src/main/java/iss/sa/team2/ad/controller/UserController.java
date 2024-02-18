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
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
    private IUserService userService;
	@Autowired
    private IRegularUserAnimeService regularUserAnimeService;

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
        String old_userId = (String) session.getAttribute("userId");
        if (old_userId!=null&& !old_userId.isEmpty()) {
        	if(userService.getUserById(old_userId).get().getPosition()==UserPosition.RegularUser) {
    			return "redirect:/user/homePage";
    		}else {
    			return "redirect:/animes/management";
    		}
        }else{
        	
        	String userId = userService.findUserIdByAccountAndPassword(account, password);
        	if(userId!=null){
        		session.setAttribute("userId", userId); 
        		if(userService.getUserById(userId).get().getPosition()==UserPosition.RegularUser) {
        			return "redirect:/user/homePage";
        		}else {
        			return "redirect:/animes/management";
        		}	
            }
            else {
            	model.addAttribute("loginError", "Invalid account or password"); 
                return "login";
            }    	
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
            RegularUser regularUser = (RegularUser) user;
            model.addAttribute("UserDetail", regularUser);
            
            List<Integer> yearRange = getYearRange();
            List<Integer> monthRange = getMonthRange();
            List<Integer> dayRange = getDayRange();
            
            List<RegularUserAnime> regularUserAnimes = regularUserAnimeService.findAllByUserIdAndType(userId, MyType.Subscription);
            
            model.addAttribute("yearRange", yearRange);
            model.addAttribute("monthRange", monthRange);
            model.addAttribute("dayRange", dayRange);
            model.addAttribute("subscriptions", regularUserAnimes );
            return "homePage"; 
        } else {
            
            return "error"; 
        }
    }
    
    @GetMapping("/follow")
    public String getUserDetails_follow(Model model,HttpSession session) {
    	String userId = (String) session.getAttribute("userId");

    	RegularUser regularUser = null;
        if (userId != null) {
            Optional<User> user = userService.getUserById(userId); 
            if (user.isPresent()) {
                regularUser = (RegularUser) user.get();
                model.addAttribute("UserDetail", regularUser);
                
                List<Integer> yearRange = getYearRange();
                List<Integer> monthRange = getMonthRange();
                List<Integer> dayRange = getDayRange();
                model.addAttribute("yearRange", yearRange);
                model.addAttribute("monthRange", monthRange);
                model.addAttribute("dayRange", dayRange);
            }
        }
    	
    	return "follow"; 
    }
    
    @GetMapping("/favourite")
    public String getUserDetails_favourite(Model model,HttpSession session) {
    	String userId = (String) session.getAttribute("userId");

    	RegularUser regularUser = null;
        if (userId != null) {
            Optional<User> user = userService.getUserById(userId); 
            if (user.isPresent()) {
                regularUser = (RegularUser) user.get();
                model.addAttribute("UserDetail", regularUser);
                
                List<Integer> yearRange = getYearRange();
                List<Integer> monthRange = getMonthRange();
                List<Integer> dayRange = getDayRange();
                model.addAttribute("yearRange", yearRange);
                model.addAttribute("monthRange", monthRange);
                model.addAttribute("dayRange", dayRange);
            }
        }
    	
    	return "favourite"; 
    }
    
    @GetMapping("/favourite_detail")
    public String getUserDetails_favourite_detail(Model model,HttpSession session) {
    	String userId = (String) session.getAttribute("userId");

    	RegularUser regularUser = null;
        if (userId != null) {
            Optional<User> user = userService.getUserById(userId); 
            if (user.isPresent()) {
                regularUser = (RegularUser) user.get();
                model.addAttribute("UserDetail", regularUser);
                
                List<Integer> yearRange = getYearRange();
                List<Integer> monthRange = getMonthRange();
                List<Integer> dayRange = getDayRange();
                model.addAttribute("yearRange", yearRange);
                model.addAttribute("monthRange", monthRange);
                model.addAttribute("dayRange", dayRange);
            }
        }
    	
    	return "favourite_detail"; 
    }
    
    @GetMapping("/profile")
    public String getUserDetails_profile(Model model,HttpSession session) {
    	String userId = (String) session.getAttribute("userId");

        RegularUser regularUser = null;
        if (userId != null) {
            Optional<User> user = userService.getUserById(userId); 
            if (user.isPresent()) {
                regularUser = (RegularUser) user.get();
                model.addAttribute("UserDetail", regularUser);
                
                List<Integer> yearRange = getYearRange();
                List<Integer> monthRange = getMonthRange();
                List<Integer> dayRange = getDayRange();
                model.addAttribute("yearRange", yearRange);
                model.addAttribute("monthRange", monthRange);
                model.addAttribute("dayRange", dayRange);
            }
        }
    	
    	return "profile"; 
    }
    
    @GetMapping("/management")
	public String showAnimeManagementPage(Model model) {
		List<User> users = userService.getAllUsers();
		if (users.isEmpty()) {
			model.addAttribute("message", "Now user list is empty.");
		} else {
			System.out.println("users：");
			for (User user : users) {
				System.out.println(user);
			}
		}
		model.addAttribute("users", users);
		return "userManagement";
	}
   
    private List<Integer> getYearRange() {
        // 返回年份范围，例如从 1900 到当前年份
        int currentYear = Year.now().getValue();
        List<Integer> yearRange = new ArrayList<>();
        for (int year = 1900; year <= currentYear; year++) {
            yearRange.add(year);
        }
        return yearRange;
    }

    private List<Integer> getMonthRange() {
        // 返回月份范围，从 1 到 12
        return IntStream.rangeClosed(1, 12).boxed().collect(Collectors.toList());
    }

    private List<Integer> getDayRange() {
        // 返回日期范围，从 1 到 31
        return IntStream.rangeClosed(1, 31).boxed().collect(Collectors.toList());
    }
}
