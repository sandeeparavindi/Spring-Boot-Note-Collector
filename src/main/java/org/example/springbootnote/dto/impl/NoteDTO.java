package org.example.springbootnote.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootnote.customObj.NoteResponse;
import org.example.springbootnote.dto.SuperDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteDTO implements SuperDTO, NoteResponse {
    private String noteId;
    private String noteTitle;
    private String noteDesc;
    private String priorityLevel;
    private String createDate;
    private  String userId;
}
