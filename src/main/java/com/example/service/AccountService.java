package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    

    public Account findUserByUsername(Account username) {
        return accountRepository.findByusername(username.getUsername()).orElse(null);
    }

    public Account createAccount(Account account) {
        if (accountRepository.findByusername(account.getUsername()).orElse(null) == null
                &&
                account.getPassword().length() > 3) {

            Account newAccount = accountRepository.save(account);
            return newAccount;
        } else {
            return null;

        }
    }

    public List<Account> userLogin(Account account) {
        if (account.getUsername() != null && account.getPassword() != null) {
            return accountRepository.
            findByUsernameAndPassword(
                account.getUsername(), account.getPassword());
        } else {return null;}
    }
}
