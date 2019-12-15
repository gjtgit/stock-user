package com.gao.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.gao.demo.entity.UserEntity;
import com.gao.demo.repository.UserRepo;

@Service
public class UserService {
    
    Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.from}")
    private String from;
    
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
    
    public void register(UserEntity u) throws Exception{
        try{
            String checkCode = UUID.randomUUID().toString().replace("-","");
            u.setCheckCode(checkCode);
            logger.info("[info]: Activate link code="+checkCode);
            userRepo.saveAndFlush(u);
            String subject = "Activate Code from StockMarketCharting";
            //String context = "<a href=\"/user/checkCode?code="+checkCode+"\">Activate by click:"+checkCode+"</a>";
            String context = "To activate your account, go to StockMarketCharting-signup, input the code "+checkCode;
            sendSimpleMail(u.getEmail(),subject,context);
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public UserEntity checkCode(String checkCode) throws Exception{
        return userRepo.findByCheckCode(checkCode);
    }
    
    public void save(UserEntity u) throws Exception{
        try{
            userRepo.saveAndFlush(u);
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public List<UserEntity> getUserList() throws Exception{
        return userRepo.findAll();
    }
    
    public UserEntity findById(Long id) throws Exception{
        UserEntity user = null;
        try {
            Optional<UserEntity> u = userRepo.findById(id);
            if(u.isPresent()) {
                user = u.get();
            }
            return user;
        }catch(Exception e) {
            throw new Exception(e);
        }
    }
    
    public UserEntity findByUsernameAndPassword(String username, String password) throws Exception{
        return userRepo.findByUsernameAndPassword(username, password);
    }        

    public UserEntity findByUsername(String username) throws Exception{
        return userRepo.findByUsername(username);
    }         
}
