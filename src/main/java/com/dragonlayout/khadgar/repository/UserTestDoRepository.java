package com.dragonlayout.khadgar.repository;

import com.dragonlayout.khadgar.model.UserTestDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestDoRepository extends JpaRepository<UserTestDo, Long> {

    UserTestDo findUserTestDoByUsername(String username);
}