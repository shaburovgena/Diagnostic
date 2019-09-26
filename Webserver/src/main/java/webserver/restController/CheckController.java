package webserver.restController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webserver.domain.User;
import webserver.repos.UserRepo;

@RestController
public class CheckController {
    private final UserRepo userRepo;

    public CheckController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/checkUser")
    public boolean checkUser (@RequestBody String username){
        if(userRepo.findByUsername(username.replace("=", "")) != null) {
            return true;
        }else{
            return false;
        }
    }
}
