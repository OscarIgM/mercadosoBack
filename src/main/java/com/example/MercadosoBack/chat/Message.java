package com.example.MercadosoBack.chat;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class Message {
    private String senderId;
    private String content;
    private LocalDateTime timestamp;
}
