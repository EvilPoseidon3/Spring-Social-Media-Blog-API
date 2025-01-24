package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */

@Controller
public class SocialMediaController {

  private final AccountService accountService;
  @SuppressWarnings("unused")
  private final MessageService messageService;

  @Autowired
  public SocialMediaController(AccountService accountService, MessageService messageService) {
    this.accountService = accountService;
    this.messageService = messageService;
  }

  @PostMapping("/register")
  public @ResponseBody ResponseEntity<Account> newUser(@RequestBody Account account) {
    if (accountService.findUserByUsername(account) != null) {
      return ResponseEntity.status(409).body(account);
    } else if (accountService.findUserByUsername(account) == null) {
      Account newAccount = accountService.createAccount(account);
      return ResponseEntity.status(HttpStatus.OK).body(newAccount);
    } else {
      return ResponseEntity.status(400).body(account);
    }
  }

  @PostMapping("/login")
  public @ResponseBody ResponseEntity<Account> UserLogin(@RequestBody Account account) {
    List<Account> checkAccount = accountService.userLogin(account);

    if (checkAccount.size() == 0) {
      return ResponseEntity.status(401).body(null);
    } else {
      return ResponseEntity.status(200).body(checkAccount.get(0));
    }
  }

  @PostMapping("messages")
  public @ResponseBody ResponseEntity<Message> postMessage(@RequestBody Message message) {

    Message newMessage = messageService.postMessage(message);

    if (newMessage == null) {
      return ResponseEntity.status(400).body(null);
    } else {
      return ResponseEntity.status(200).body(newMessage);
    }

  }

  @GetMapping("messages")
  public @ResponseBody ResponseEntity<List<Message>> getAllMessages() {
    return ResponseEntity.status(200).body(messageService.getMessages());
  }

  @GetMapping("messages/{messageId}")
  public @ResponseBody ResponseEntity<Optional<Message>> getMessage(@PathVariable int messageId) {
    Optional<Message> returnedMessage = messageService.getMessage(messageId);

    if (returnedMessage.isEmpty()) {
      return ResponseEntity.status(200).body(null);
    } else {
      return ResponseEntity.status(200).body(returnedMessage);
    }
  }

  @DeleteMapping("messages/{messageId}")
  public @ResponseBody ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {

    if (!messageService.getMessage(messageId).isEmpty()) {
      messageService.deleteMessage(messageId);
      return ResponseEntity.status(200).body(1);
    } else {
      return ResponseEntity.status(200).body(null);
    }
  }

  @PatchMapping("messages/{messageId}")
  public @ResponseBody ResponseEntity<Integer> updateMessage(
      @PathVariable int messageId,
      @RequestBody Message message) {

    int numUpt = messageService.updateMessage(message.getMessageText(), messageId);

    System.out.println(numUpt);
    if (numUpt != 1) {
      return ResponseEntity.status(400).body(null);
    } else {
      return ResponseEntity.status(200).body(numUpt);
    }

  }

  @GetMapping("accounts/{accountId}/messages")
  public @ResponseBody ResponseEntity<List<Message>> getUserMessages(
      @PathVariable int accountId) {
    return ResponseEntity.status(200).body(messageService.getUserMessages(accountId));
  }
}
