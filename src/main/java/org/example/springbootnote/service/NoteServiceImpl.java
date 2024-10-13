package org.example.springbootnote.service;

import org.example.springbootnote.customObj.NoteErrorResponse;
import org.example.springbootnote.customObj.NoteResponse;
import org.example.springbootnote.dao.NoteDAO;
import org.example.springbootnote.dto.impl.NoteDTO;
import org.example.springbootnote.entity.NoteEntity;
import org.example.springbootnote.exception.DataPersistFailedException;
import org.example.springbootnote.exception.NoteNotFound;
import org.example.springbootnote.util.AppUtil;
import org.example.springbootnote.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service // Component annotation eka meta anotate krla thinne service annotation eka athule
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDAO noteDAO;

    @Autowired
    private Mapping mapping;


    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.createNote());
        var noteEntity = mapping.convertToEntity(noteDTO);
        var savedNoted = noteDAO.save(noteEntity);
        if (savedNoted == null) {
            throw new DataPersistFailedException("Cannot save Note");
        }

    }

    @Override
    public void updateNote(String noteId, NoteDTO incomeNoteDTO) {
        Optional<NoteEntity> tmpNoteEntity= noteDAO.findById(noteId);
        if(!tmpNoteEntity.isPresent()){
            throw new NoteNotFound("Note not found");
        }else {
            tmpNoteEntity.get().setNoteDesc(incomeNoteDTO.getNoteDesc());
            tmpNoteEntity.get().setNoteTitle(incomeNoteDTO.getNoteTitle());
            tmpNoteEntity.get().setCreateDate(incomeNoteDTO.getCreateDate());
            tmpNoteEntity.get().setPriorityLevel(incomeNoteDTO.getPriorityLevel());
        }
    }

    @Override
    public void deleteNote(String noteId) {
        //noteDAO.deleteById(noteId);
        Optional<NoteEntity> findId = noteDAO.findById(noteId);
        if (!findId.isPresent()) {
            throw new NoteNotFound("Note not found");
        } else {
            noteDAO.deleteById(noteId);
        }
    }

    @Override
    public NoteResponse getSelectedNote(String noteId) {
        if(noteDAO.existsById(noteId)){
            return mapping.convertToDTO(noteDAO.getReferenceById(noteId));
        }else {
            return new NoteErrorResponse(0,"Note not found");
        }
    }
    @Override
    public List<NoteDTO> getAllNotes() {
//       List<NoteEntity> getAllNotes = noteDAO.findAll();
//       List<NoteDTO> noteDTO= mapping.convertToDTO(getAllNotes)
//
//        return noteDTO;

       return mapping.convertToDTO(noteDAO.findAll());
    }
}
