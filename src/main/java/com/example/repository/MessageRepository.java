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
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Modifying
    @Query("delete from Message where messageId = :messageId")
    @Transactional
    int deleteById(@Param("messageId") int messageId);

    List<Message> findBypostedBy(int accountId);

    @Modifying
    @Query("update Message m set m.messageText = :messageText where m.messageId = :messageId")
    @Transactional
    int updateMessage(@Param("messageText") String messageText, @Param("messageId") Integer messageId);
}
