package org.ac.cst8277.liang.ping.service;

import org.ac.cst8277.liang.ping.entity.Message;
import org.ac.cst8277.liang.ping.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<Message> getMessagesByUser(Long userId) {
        return messageRepository.findByUserUserId(userId);
    }

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }
}
