package system.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import system.model.DTO.UserDTO;
import system.service.UserService;



@Controller
public class LoginController {
	
	@Autowired
    private UserService userService;
	 @GetMapping("/")
	    public String showLoginForm() {
	        return "login"; 
	    }
	 

	    @PostMapping("/Login")
	    public String login(@RequestParam("email") String email,
	                        @RequestParam("password") String password,
	                        Model model,HttpSession session) {
	        UserDTO userDTO = userService.login(email, password);
	        if (userDTO != null) {
	        	if ("SuperAdmin".equals(userDTO.getRoleName())) {
	        		session.setAttribute("admin", userDTO);
	        		return "redirect:/Admin/home";
	        	}
	        	if("Teacher".equals(userDTO.getRoleName())) {
	        		session.setAttribute("teacher", userDTO);
	        		return "redirect:/Teacher/home";
	        	}
	        	if("Student".equals(userDTO.getRoleName())) {
	        		session.setAttribute("student", userDTO);
	        		return "redirect:/Student/home";
	        	}
	        }
			return "login";
	    }
	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        // Invalidate the current session
	        session.invalidate();

	        // Redirect to the login page or home page
	        return "redirect:/";
	    }
	    

}
