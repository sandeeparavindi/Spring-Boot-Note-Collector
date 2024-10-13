package org.example.springbootnote.dao;


import org.example.springbootnote.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<UserEntity,String> {
    UserEntity getUserEntityByUserId(String userId);

}
