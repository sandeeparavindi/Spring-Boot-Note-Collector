package org.example.springbootnote.dao;


import org.example.springbootnote.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDAO extends JpaRepository<NoteEntity,String> {



}
