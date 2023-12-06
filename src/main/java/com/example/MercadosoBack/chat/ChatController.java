package com.example.MercadosoBack.chat;

import com.example.MercadosoBack.chat.request.ChatRoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost/5173")
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/create")
    public ChatRoom createChatRoom(@RequestBody ChatRoomRequest chatRoomRequest) {
        return chatRoomService.createChatRoom(
                chatRoomRequest.getSellerId(),
                chatRoomRequest.getBuyerId(),
                chatRoomRequest.getProductId()
        );
    }

    @GetMapping("/getAllRoomsForUser")
    public List<ChatRoom> getAllChatRoomsForUser(@RequestParam String userId){
        return chatRoomService.getAllChatRoomsForUser(userId);
    }

    @GetMapping("/get")
    public Optional<ChatRoom> getChatRoom(@RequestParam String sellerId, @RequestParam String buyerId) {
        return chatRoomService.getChatRoom(sellerId, buyerId);
    }
    @GetMapping("/getById")
    public Optional<ChatRoom> getById(@RequestParam String id){
        return chatRoomService.getById(id);
    }
    @GetMapping("/getAllRooms")
    public List<ChatRoom> getChatRoomAllRooms(){
        return chatRoomService.getAllChatRooms();
    }

    @PostMapping("/sendMessage")
    public Message sendMessage(@RequestParam String roomId, @RequestParam String senderId, @RequestParam String content) {
        Message message = Message.builder()
                .senderId(senderId)
                .content(content)
                .timestamp(LocalDateTime.now())
                .build();
        messageService.saveMessage(roomId, message);

        return message;
    }

    @GetMapping("/getMessages")
    public List<Message> getMessages(@RequestParam String roomId) {
        return messageService.getMessagesByRoomId(roomId);
    }
}
