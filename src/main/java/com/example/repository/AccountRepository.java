package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import com.example.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByaccountId(Integer accountID);

    @SuppressWarnings("unchecked")
    Account save(Account account);

    Optional<Account> findByusername(String username);

    List<Account> findByUsernameAndPassword(String username, String password);

}

/*
 *AccountService.class]: Unsatisfied dependency expressed through constructo
 r parameter 0; nested exception is org.springframework.beans.factory.BeanCrea
 tionException: Error creating bean with name 'accountRepository' defined in 
 com.example.repository.AccountRepository defined in @EnableJpaRepositories decl
 ared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation o
 f init method failed; nested exception is org.springframework.data.repository.quer
 y.QueryCreationException: Could not create query for public abstract java.util.Opti
 onal com.example.repository.AccountRepository.findByusernameAndpassword(java.lang.S
 tring,java.lang.String)! Reason: Failed to create query for method public abstract j
 ava.util.Optional com.example.repository.AccountRepository.findByusernameAndpassword
 java.lang.String,java.lang.String)! No property andpassword found for type String! Tr
 aversed path: Account.username.; nested exception is java.lang.IllegalArgumentExcepti
 on: Failed to create query for method public abstract java.util.Optional com.example.
 repository.AccountRepository.findByusernameAndpassword(java.lang.String,java.lang.Str
 ing)! No property andpassword found for type String! Traversed path: Account.username.
 

 * 
 * 
 * 
 */
