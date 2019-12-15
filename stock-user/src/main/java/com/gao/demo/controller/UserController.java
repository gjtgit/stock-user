package com.gao.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gao.demo.entity.UserEntity;
import com.gao.demo.model.UserVo;
import com.gao.demo.service.UserService;

@RestController
public class UserController {
    
    Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(path="/users/{id}",method=RequestMethod.GET)
    public UserEntity getUser(@PathVariable Long id) throws Exception{
        return this.userService.findById(id);
    }
    
    @RequestMapping(path="/users",method=RequestMethod.GET)
    public List<UserEntity> getUserList() throws Exception{
        return this.userService.getUserList();
    }
    
    @RequestMapping(path="/register", method=RequestMethod.POST)
    public String regUser(@RequestBody UserVo vo) throws Exception {
        UserEntity u = new UserEntity();
        u.setUsername(vo.getUsername());
        u.setPassword(vo.getPassword());
        u.setUserType(vo.getUserType());
        u.setEmail(vo.getEmail());
        u.setMobile(vo.getMobile());
        u.setConfirmed(0);
        this.userService.register(u);
        return "Register user successfully, to activate the user by using the code in the confirm mail sent to you.";
    }
    
    @RequestMapping(value = "/user/checkCode/{code}", method=RequestMethod.POST)
    public String checkCode(@PathVariable String code) throws Exception{
        UserEntity u = userService.checkCode(code);
        if (u !=null){
           u.setConfirmed(1);
           u.setCheckCode("");
           userService.save(u);
        }
        return "{\"str\":\"1\"}";
    }
    
    @RequestMapping(path="/users", method=RequestMethod.POST)
    public UserEntity addUser(@RequestBody UserVo vo) throws Exception {
        UserEntity u = new UserEntity();
        u.setUsername(vo.getUsername());
        u.setPassword(vo.getPassword());
        u.setUserType(vo.getUserType());
        u.setEmail(vo.getEmail());
        u.setMobile(vo.getMobile());
        u.setConfirmed(0);
        this.userService.register(u);
        return u;
    }
    
    @RequestMapping(path="/users", method=RequestMethod.PUT)
    public UserEntity updateUser(@RequestBody UserVo vo) throws Exception {
        UserEntity u = this.userService.findById(vo.getId());
        if(u!=null) {
            u.setUsername(vo.getUsername());
            u.setPassword(vo.getPassword());
            u.setUserType(vo.getUserType());
            u.setEmail(vo.getEmail());
            u.setMobile(vo.getMobile());
            this.userService.save(u);
        }
        return u;
    }
    
    @RequestMapping(path="/login", method=RequestMethod.GET)
    public UserEntity login(@RequestParam String username, @RequestParam String password) throws Exception {
        UserEntity u = this.userService.findByUsernameAndPassword(username, password);
        if(u!=null) {
            if (u.getConfirmed()!=1) {
                logger.info("Please activate your account first.");
            }
        }
        return u;
    }
    
    @GetMapping(value="/getUserByName")
    public UserEntity getUserByName(@RequestParam String username) throws Exception {
        UserEntity u = this.userService.findByUsername(username);
        return u;
    }
    
    @PostMapping("/test")
    public String test(){
        return "{\"str\":\"from stock-user post\"}";
    }
}
