package com.example.MercadosoBack.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private MessageRepository messageRepository;

    public ChatRoom createChatRoom(String sellerId, String buyerId, String productId) {
        ChatRoom chatRoom = ChatRoom.builder()
                .sellerId(sellerId)
                .buyerId(buyerId)
                .productId(productId)
                .build();

        return chatRoomRepository.save(chatRoom);
    }
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }
    public List<ChatRoom> getAllChatRoomsForUser(String userId) {
        return chatRoomRepository.findBySellerIdOrBuyerId(userId, userId);
    }
    public Optional<ChatRoom> getById(String id){
        return chatRoomRepository.findById(id);
    }

    public Optional<ChatRoom> getChatRoom(String sellerId, String buyerId) {
        return chatRoomRepository.findBySellerIdAndBuyerId(sellerId, buyerId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBySenderId(String senderId) {
        return messageRepository.findBySenderId(senderId);
    }
}
