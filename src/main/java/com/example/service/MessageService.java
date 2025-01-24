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
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message postMessage(Message message) {
        if (message.getMessageText().length() <= 255 &&
                message.getMessageText().length() > 0 &&
                !accountRepository.findById(message.getPostedBy()).isEmpty()) {
            return messageRepository.save(message);
        } else {
            return null;
        }
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessage(int messageId) {
        return messageRepository.findById(messageId);
    }

    public int deleteMessage(int messageId) {
        return messageRepository.deleteById(messageId);
    }

    public int updateMessage(String text, int id) {

        if (text.length() > 0 &&
                text.length() <= 255 &&
                !messageRepository.findById((Integer) id).isEmpty()) {

            int response = messageRepository.updateMessage(text, id);
            return response;
        } else {
            return 0;
        }
    }

    public List<Message> getUserMessages(int accountId) {
        return messageRepository.findBypostedBy(accountId);
    }
}
