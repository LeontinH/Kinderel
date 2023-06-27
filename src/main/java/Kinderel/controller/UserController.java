package Kinderel.controller;

import Kinderel.model.DTO;
import Kinderel.model.User;
import Kinderel.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewWellcomePage(){
        return "wellcomePage";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginPage";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        DTO user = new DTO();
        model.addAttribute("user", user);
        return "registerPage";
    }

    @GetMapping("/pageInConstruction")
    public String showPageInConstruction (Model model) {
        DTO user = new DTO();
        model.addAttribute("user", user);
        return "pageInConstruction";
    }

    @PostMapping("/register")
    private String registration(@RequestParam("file") MultipartFile file, @Valid @ModelAttribute("user") DTO user,
                                BindingResult result, Model model) throws IOException, ServletException {
        User existing = userService.findByUserName(user.getUserName());
        if (existing != null) {
            result.rejectValue("userName", null);
            return "errorPage";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "registerPage";
        }
        user.setProfilePicture(file.getBytes());
        userService.saveUser(user);
        model.addAttribute("user", user);
        return "loginPage";
    }

    @GetMapping("/image")
    @ResponseBody
    public void showImage(Authentication authentication , HttpServletResponse response)
            throws ServletException, IOException {
        User user = userService.findByUserName(authentication.getName());
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif, image/pdf");
        response.getOutputStream().write(user.getProfilePicture());
        response.getOutputStream().close();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<DTO> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "usersListPage";
    }
}