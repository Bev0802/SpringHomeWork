import com.bev0802.webclient.Servises.WebServise;
import com.geekbrains.bev0802.notes.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class WebController {
    private final WebServise webServis;
    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    public WebController(WebServise webServis) {
        this.webServis = webServis;
    }

    @GetMapping("/users")
    public String userList(Model model) {
        List<User> users = webServis.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String showAddFormUsers(Model model) {
        model.addAttribute("user", new User());
        return "usercreate";
    }

    @PostMapping("/users/new")
    public String addUser(@ModelAttribute User user) {
        webServis.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}/update")
    public String showUpdateUserForm(@PathVariable Long userId, Model model) {
        User user = webServis.findUserById(userId);
        model.addAttribute("user", user);
        return "userupdate";
    }

    @PutMapping("/users/update/{userId}")
    public String updateUser(@PathVariable Long userId, @ModelAttribute User updatedUser) {
        webServis.updateUser(userId, updatedUser);
        return "redirect:/users";
    }