package team4bbs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team4bbs.DTO.UserDto;
import team4bbs.Model.User;
import team4bbs.Service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/account")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String login(){
        return "account/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userDto",new UserDto());
        return "account/register";
    }
    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "account/register";
        }
        try {
            User user = User.createUser(userDto, passwordEncoder);
            userService.save(user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "account/register";
        }
        return "redirect:/";
    }

}
