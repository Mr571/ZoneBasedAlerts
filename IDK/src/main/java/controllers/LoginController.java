package controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Vector;
import model.*;
/*
@RestController
public class LoginController {

    @PostMapping(path = "login/UserLogin")
    public int login (@RequestParam("Username") String username, @RequestParam("Password") String password)
    {
        for(int i = 1; i <= usersMap.size(); i++)
        {
            if(usersMap.get(i).getUsername().equals(username))
            {
                if(passwordMap.get(i).equals(password + usersMap.get(i).getSalt()))
                {
                    return i;
                }
            }
        }
        System.out.print("Username or password incorrect");
        return -1;
    }
}
*/
