package com.example.MercadosoBack.chat;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySellerIdAndBuyerId(String sellerId, String buyerId);
    List<ChatRoom> findBySellerIdOrBuyerId(String sellerId, String buyerId);
}