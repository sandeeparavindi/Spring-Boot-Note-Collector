package org.example.springbootnote.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springbootnote.customObj.UserErrorResponse;
import org.example.springbootnote.customObj.UserResponse;
import org.example.springbootnote.dao.UserDAO;
import org.example.springbootnote.dto.impl.UserDTO;
import org.example.springbootnote.entity.UserEntity;
import org.example.springbootnote.exception.DataPersistFailedException;
import org.example.springbootnote.exception.UserNotFoundException;
import org.example.springbootnote.util.AppUtil;
import org.example.springbootnote.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private  final Mapping mapping;

    @Override
    public void saveUser(UserDTO userDTO) {
        userDTO.setUserId(AppUtil.createUserId());
        UserEntity savedUser =
                userDAO.save(mapping.convertToUserEntity(userDTO));
        if(savedUser == null && savedUser.getUserId() == null ) {
            throw new DataPersistFailedException("Cannot data saved");
        }
    }
    @Override
    public void updateUser(UserDTO userDTO) {
        Optional<UserEntity> tmpUser = userDAO.findById(userDTO.getUserId());
        if(!tmpUser.isPresent()){
            throw  new UserNotFoundException("User Not Found");
        }else {
            tmpUser.get().setFirstName(userDTO.getFirstName());
            tmpUser.get().setLastName(userDTO.getLastName());
            tmpUser.get().setEmail(userDTO.getEmail());
            tmpUser.get().setPassword(userDTO.getPassword());
            tmpUser.get().setProfilePic(userDTO.getProfilePic());
        }
    }
    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> selectedUserId = userDAO.findById(userId);
        if(!selectedUserId.isPresent()){
            throw new UserNotFoundException("User not found");
        }else {
            userDAO.deleteById(userId);
        }
    }
    @Override
    public UserResponse getSelectedUser(String userId) {
        if(userDAO.existsById(userId)){
            UserEntity userEntityByUserId = userDAO.getUserEntityByUserId(userId);
            return mapping.convertToUserDTO(userEntityByUserId);
            }else {
                return new UserErrorResponse(0, "User not found");
        }
    }
    @Override
    public List<UserDTO> getAllUsers() {
      List<UserEntity> getAllUsers = userDAO.findAll();
      return  mapping.convertUserToDTOList(getAllUsers);
    }
}
