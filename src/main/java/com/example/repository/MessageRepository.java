package com.example.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;


@Repository
public interface MessageRepository extends JpaRepository<Message,Integer>{

    Message findBymessageId(Integer messageId);

    Message save(Message message);

    List<Message> findAll();

    void deleteById(int messageId);

    @Modifying
    @Query("UPDATE message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    Integer updateMessage(@Param("messageText") String messageText,@Param("messageId") Integer messageId );
}


/*

MessageService.class]: Unsatisfied dependency expressed through
 constructor parameter 0; nested exception is org.springframewor
 k.beans.factory.BeanCreationException: Error creating bean with 
 name 'messageRepository' defined in com.example.repository.Message
 Repository defined in @EnableJpaRepositories declared on JpaReposit
 oriesRegistrar.EnableJpaRepositoriesConfiguration: Invocation of ini
 t method failed; nested exception is org.springframework.data.repo
 sitory.query.QueryCreationException: Could not create query for pu
 blic abstract java.lang.Integer com.example.repository.MessageRepo
 sitory.updateMessage(java.lang.String,java.lang.Integer)! Reason: 
 Validation failed for query for method public abstract java.lang.
 Integer com.example.repository.MessageRepository.updateMessage(ja
 va.lang.String,java.lang.Integer)!; nested exception is java.lang.
 IllegalArgumentException: Validation failed for query for method p
 ublic abstract java.lang.Integer com.example.repository.MessageRepo
 sitory.updateMessage(java.lang.String,java.lang.Integer)!
 
 

 

 
 */