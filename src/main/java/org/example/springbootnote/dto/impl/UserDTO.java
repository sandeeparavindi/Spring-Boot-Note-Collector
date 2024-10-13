package org.example.springbootnote.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootnote.customObj.UserResponse;
import org.example.springbootnote.dto.SuperDTO;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements SuperDTO, UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profilePic;
    private List<NoteDTO> notes = new ArrayList<>();
}
