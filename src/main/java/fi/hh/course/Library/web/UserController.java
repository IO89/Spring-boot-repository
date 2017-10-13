package fi.hh.course.Library.web;

import fi.hh.course.Library.domain.SignUpForm;
import fi.hh.course.Library.domain.User;
import fi.hh.course.Library.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "signup")
    public String addUser(Model model) {
        model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }

    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignUpForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {//check for an error
            if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {//if password match with re entered pass
                String pswd = signupForm.getPassword();
                BCryptPasswordEncoder cryptedpswd = new BCryptPasswordEncoder();
                String hashPswd = cryptedpswd.encode(pswd);

                User newUser = new User();
                newUser.setPasswordHash(hashPswd);
                newUser.setUsername(signupForm.getUsername());
                newUser.setRole("USER");

                if (userRepository.findByUsername(signupForm.getUsername()) == null) {//if available user name
                    userRepository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already registered");
                    return "signup";
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords are not identical");
                return "signup";

            }
        } else {
            return "signup";
        }
        return "redirect:/login";

    }
}



