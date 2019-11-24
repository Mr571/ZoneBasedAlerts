package controllers;

import org.springframework.web.bind.annotation.*;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/*
@RestController
public class UserController {

    protected static HashMap<Integer, User> usersMap = new HashMap<>();
    protected static HashMap<Integer, UserDTO> userDTOMap = new HashMap<>();
    protected static HashMap<Integer, String> passwordMap = new HashMap<>();

    private int userID = usersMap.size() + 1;

    @GetMapping(path = "Users/getUsername") //DTO
    public String getUsername(@RequestParam("ID") int ID) {
        return userDTOMap.get(ID).getUsername();
    }

    @GetMapping(path = "Users/getProfilePicture")//DTO
    public String getProfilePicture(@RequestParam("ID") int ID) {
        return userDTOMap.get(ID).getPicture();
    }

    @GetMapping(path = "Users/getBio") //DTO
    public String getBio(@RequestParam("ID") int ID) {
        return userDTOMap.get(ID).getBio();
    }

    @GetMapping(path = "Users/getNotifications") //Does not use DTO, private
    public ArrayList<String> getNotifications(@RequestParam("ID") int ID) {
        return usersMap.get(ID).getNotifications();
    }

    @GetMapping(path = "Users/getTheme") //Does not use DTO, private
    public int getTheme(@RequestParam("ID") int ID) {
        User temp = new User();
        temp = usersMap.get(ID);
        return temp.getTheme();
    }

    @GetMapping(path = "Users/getSubscribers") //DTO
    public ArrayList<Integer> getSubscribers(@RequestParam("ID") int ID) {
        return usersMap.get(ID).getSubscribers();
    }

    @GetMapping(path = "Users/getSubscriptions") //DTO
    public ArrayList<Integer> getSubscriptions(@RequestParam("ID") int ID) {
        return usersMap.get(ID).getSubscriptions();
    }

    @GetMapping(path = "Users/getPassword") //Returns hashed password
    public int getPassword(@RequestParam("ID") int ID) {
        return passwordMap.get(ID).hashCode();
    }

    @GetMapping(path = "Users/getUserDTO")
    public User getUser(@RequestParam("ID") int ID)
    {
        return usersMap.get(ID);
    }

    @GetMapping(path = "Users/getUser")
    public UserDTO getUserDTO(@RequestParam("ID") int ID)
    {
        return userDTOMap.get(ID);
    }

    @GetMapping(path = "Users/getUserID")
    public int getUserID(@RequestParam("Username") String username)
    {
        int i = 1;
        while (i <= usersMap.size()) {
            if (usersMap.get(i).getUsername().equals(username)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @PostMapping(path = "Users/createUser")
    public void createUser(@RequestBody User newUser, @RequestParam("Password") String password) {
            int i = 1;
            boolean test = false;
            while (i < usersMap.size())
            {
                if (usersMap.get(i).getUsername().equals(newUser.getUsername()))
                {
                    test = true;
                }
                i++;
            }
            if (test) {
                System.out.print("Username already exists, try a different name\n");
                Exception e;
            } else if (test == false) {
                ArrayList<String> string = new ArrayList<>();
                string.add("This is your first notification");
                ArrayList<Integer> ints = new ArrayList<>();
                Random rand = new Random();
                int random = rand.nextInt(10000);
                User user = new User(newUser.getUsername(), userID, newUser.isAdmin(), random);
                user.setNotifications(string);
                user.setSubscribers(ints);
                user.setSubscriptions(ints);
                user.setTheme(0);
                UserDTO userDTO = new UserDTO();
                userDTO.setAdmin(newUser.isAdmin());
                userDTO.setID(userID);
                userDTO.setUsername(newUser.getUsername());
                usersMap.put(userID, user);
                userDTOMap.put(userID, userDTO);
                passwordMap.put(userID, password + random);
                userID++;
            }
    }

    @PutMapping(path = "Users/changePicture") //updates DTO and model
    public void changePicture(@RequestParam("ID") int ID, @RequestParam("Picture") String pic) {
        if(pic == null)
        {
            usersMap.get(ID).setPicture("default");
            userDTOMap.get(ID).setPicture("default");
        }
        else {
            usersMap.get(ID).setPicture(pic);
            userDTOMap.get(ID).setPicture(pic);
        }
    }

    @PutMapping(path = "Users/changePassword") //needs to be secured
    public void changePassword(@RequestParam("ID") int ID, @RequestParam("Password") String password) {
        if (password == null)
        {
            System.out.print("Enter a password\n");
        }
        else {
            password += usersMap.get(ID).getSalt();
            passwordMap.replace(ID, password);
        }
    }

    @PutMapping(path = "Users/changeBio") //updates DTO and model
    public void changeBio(@RequestParam("ID") int ID, @RequestParam("Bio") String bio) {
        if (bio == null)
        {
            System.out.print("Enter a bio\n");
        }
        else {
            usersMap.get(ID).setBio(bio);
            userDTOMap.get(ID).setBio(bio);
        }
    }

    @PutMapping(path = "Users/changeTheme") //Does not use a DTO
    public void changeTheme(@RequestParam("ID") int ID, @RequestParam("Theme") int theme) {
        if (theme == usersMap.get(ID).getTheme())
        {
            System.out.print("That's the same theme\n");
        }
        else {
            usersMap.get(ID).setTheme(theme);
        }
    }

    @PutMapping(path = "Users/subscribe") //updates DTO and model
    public void subscribe(@RequestParam("ID") int ID, @RequestParam("Subscription") int sub) {
        if (ID == sub)
        {
            System.out.print("You can't sub yourself\n");
        }
        else {
            ArrayList<Integer> temp;
            temp = usersMap.get(ID).getSubscriptions();
            temp.add(sub);
            usersMap.get(ID).setSubscriptions(temp);
            userDTOMap.get(ID).setSubscriptions(temp);
            temp = usersMap.get(sub).getSubscribers();
            temp.add(ID);
            usersMap.get(sub).setSubscribers(temp);
            userDTOMap.get(sub).setSubscribers(temp);
        }
    }

    @PutMapping(path = "Users/unsubscribe") //updates DTO and model
    public void unsubscribe(@RequestParam("ID") int ID, @RequestParam("Subscription") int sub) {
        if (ID == sub)
        {
            System.out.print("You can't sub yourself, so you can't unsub yourself\n");
        }
        else {
            ArrayList<Integer> temp;
            for (int i = 0; i < usersMap.get(ID).getSubscriptions().size(); i++) {
                if (usersMap.get(ID).getSubscriptions().get(i) == sub) {
                    temp = usersMap.get(ID).getSubscriptions();
                    temp.remove(i);
                    usersMap.get(ID).setSubscriptions(temp);
                    userDTOMap.get(ID).setSubscriptions(temp);
                }
            }
            for (int i = 0; i < usersMap.get(sub).getSubscribers().size(); i++) {
                if (usersMap.get(sub).getSubscribers().get(i) == ID) {
                    temp = usersMap.get(sub).getSubscribers();
                    temp.remove(i);
                    usersMap.get(sub).setSubscribers(temp);
                    userDTOMap.get(sub).setSubscribers(temp);
                }
            }
        }
    }

    @PutMapping(path = "Users/deleteNotification") //Does not use a DTO
    public void deleteNotification(@RequestParam("ID") int ID, @RequestParam("Notification") int no)
    {
        if(no >= usersMap.get(ID).getNotifications().size())
        {
            System.out.print("Can't delete what isn't there\n");
        }
        else {
            ArrayList<String> temp;
            temp = usersMap.get(ID).getNotifications();
            temp.remove(no - 1);
            usersMap.get(ID).setNotifications(temp);
        }
    }

    @PutMapping(path = "Users/sendNotification")
    public void sendNotification(@RequestParam("ID") int ID, @RequestParam("Notification") String notification)
    {
        ArrayList<String> temp;
        temp = usersMap.get(ID).getNotifications();
        temp.add(notification);
        usersMap.get(ID).setNotifications(temp);
    }

    @DeleteMapping(path = "Users/delete")
    public void deleteUser (@RequestParam("ID") int ID)
    {
        if(usersMap.get(ID) == null)
        {
            System.out.print("Can't delete account that does not exist.");
        }
        else {
            usersMap.remove(ID);
            passwordMap.remove(ID);
            userDTOMap.remove(ID);
        }
    }
}
*/