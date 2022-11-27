package com.example.RestAPIController;
import java.util.*;

import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class MyControllers {
    HashMap<Integer,User> users=new HashMap<>();

    //  http://localhost:8080/get_users
    @GetMapping("/get_users")
    public List<User> getAllUsers(){
        List<User> listOfUsers=new ArrayList<>();
        for(Map.Entry<Integer,User> entry:users.entrySet()){
            listOfUsers.add(entry.getValue());
        }
        return listOfUsers;
    }

    //  http://localhost:8080/get_user?id=10
    @GetMapping("/get_user")
    public User getUser(@RequestParam("id") int id){
        if(users.containsKey(id)){
            return users.get(id);
        }else{
            return null;
        }
    }

    //  http://localhost:8080/add_user?id=43&name="Abida"&country="India"&age=24
    @PostMapping("/add_user")
    public void addUser(@RequestParam("id") int id, @RequestParam("name") String name,
                        @RequestParam("country") String country, @RequestParam("age") int age){
        User newUser=new User(id,name,country,age);
        users.put(id,newUser);
    }

    //  http://localhost:8080/add_user_body
    @PostMapping("/add_user_body")
    public void addUserBody(@RequestBody(required = true)User newUser){
        users.put(newUser.getId(),newUser);
    }

    //  http://localhost:8080/update_user
    @PutMapping("/update_user")
    public User updateUser(@RequestBody() User user){
        users.put(user.getId(),user);
        return user;
    }

   //  //  http://localhost:8080/delete_user?id=5
    //@DeleteMapping("/delete_user")
    /* public void deleteUser(@RequestParam("id") int id){
        users.remove(id);
    }   */

    //  http://localhost:8080/delete_user/id
    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable("id") Integer id){

        users.remove(id);
    }

}
