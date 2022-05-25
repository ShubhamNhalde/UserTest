package com.bridgelabz.lsm.service;

import com.bridgelabz.lsm.dto.LoginDTO;
import com.bridgelabz.lsm.dto.UserDTO;
import com.bridgelabz.lsm.model.User;

import java.util.List;

public interface IUserService {

    String registerUser(UserDTO dto);

    List<User> getUser();

    User loginUser(LoginDTO dto);


}
