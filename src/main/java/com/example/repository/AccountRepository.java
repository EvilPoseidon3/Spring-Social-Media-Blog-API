package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import com.example.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByusername(String username);

    List<Account> findByUsernameAndPassword(String username, String password);

}
