package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {


    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message postMessage(Message message){
        if (message.getMessageText().length() <= 255 && 
        message.getMessageText().length() > 0 && 
        accountRepository.findByaccountId(message.getPostedBy()) != null) {
            return messageRepository.save(message);
        } else {return null;}
    }

    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    public Message getMessage(int messageId){
       return messageRepository.findBymessageId(messageId);
    }

    public void deleteMessage(int messageId){
        messageRepository.deleteById(messageId);
    }

    public Integer updateMessage(String text, int id){

        if(text.length() > 0 &&
         text.length() <=255 && 
         messageRepository.findBymessageId(id) != null){
        return messageRepository.updateMessage(text, id);
         } else {return null;}
    }
}
