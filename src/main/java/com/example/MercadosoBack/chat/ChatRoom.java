package com.example.MercadosoBack.chat;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "chatRooms")
public class ChatRoom {
    @Id
    private String id;
    private String sellerId;
    private String buyerId;
    private String productId;
    private List<Message> messages = new ArrayList<>();
}
