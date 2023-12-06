package com.example.MercadosoBack.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
   List<Message> findBySenderId(String senderId);
   // Puedes agregar otros métodos según tus necesidades
}