package org.example.springbootnote.service;


import org.example.springbootnote.customObj.UserResponse;
import org.example.springbootnote.dto.impl.UserDTO;

import java.util.List;

public interface UserService {

    void saveUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    void deleteUser(String userId);
    UserResponse getSelectedUser(String userId);
    List<UserDTO> getAllUsers();
}
