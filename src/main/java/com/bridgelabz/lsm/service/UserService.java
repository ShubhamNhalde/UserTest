package com.bridgelabz.lsm.service;

import com.bridgelabz.lsm.dto.LoginDTO;
import com.bridgelabz.lsm.dto.UserDTO;
import com.bridgelabz.lsm.exception.PasswordException;
import com.bridgelabz.lsm.exception.UserException;
import com.bridgelabz.lsm.model.User;
import com.bridgelabz.lsm.repository.UserRepository;
import com.bridgelabz.lsm.util.EmailSenderService;
import com.bridgelabz.lsm.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository repo;

    @Autowired
    EmailSenderService sender;

    @Autowired
    TokenUtil tokenUtil;

    @Override
    //to register user
    public String registerUser(UserDTO dto) {
        Optional<User> matcher = repo.findByEmail(dto.getEmail());
        if (matcher.isPresent())
        {
            throw new UserException("Email Already Registered");
        }
        else
        {
            User user = new User(dto);
            repo.save(user);
            String token = tokenUtil.createToken(user.getUserId());
            this.sender.sendEmail(user.getEmail(), "User successfully registered", "Hi, " + user.getFirstName() + " " + user.getLastName() + " Welcome to user application \n"
                    +"\n click on following link to retrieve data : \n http://localhost:9000/user/getAll/"
                    + token);
            return token;
        }
    }

    //to retrieve list of all users
    @Override
    public List<User> getUser() {
        List<User> list = repo.findAll();
        if (list.isEmpty()){
            throw new UserException("There are no users added");
        }else {
         return list;
        }
    }


    @Override
    public User loginUser(LoginDTO dto) {
        Optional<User> user = repo.findByEmail(dto.getEmail());
        if (user.get().equals(null)){
            throw new UserException(" There are no user with given email id ");
        }else {
            if (!dto.getPassword().equals(user.get().getPassword())){
                throw new PasswordException(" Invalid password ");
            }else {
                sender.sendEmail(user.get().getEmail(), "User successfully login", "Hi, " + user.get().getFirstName()
                        + " Welcome to user application \n"
                        + "to get account information : \n"
                        + " http://localhost:9000/user/getAll" + tokenUtil.createToken(user.get().getUserId()));
                return user.get();
            }
        }
    }
}
