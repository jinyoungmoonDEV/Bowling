package com.example.Bowling.repository;

import com.example.Bowling.domain.dto.UserDTO;
import com.example.Bowling.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    UserDTO findByName(String name);

}
