package com.example.MercadosoBack.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public Message saveMessage(String roomId, Message message) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        chatRoom.getMessages().add(message);
        chatRoomRepository.save(chatRoom);
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByRoomId(String roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));
        return chatRoom.getMessages();
    }
}
