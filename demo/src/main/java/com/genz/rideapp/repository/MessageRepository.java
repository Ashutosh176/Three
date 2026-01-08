package com.genz.rideapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.genz.rideapp.model.Message;
import com.genz.rideapp.model.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Ye query check karti hai:
    // (Sender = Me AND Receiver = You)  OR  (Sender = You AND Receiver = Me)
    // Aur fir time ke hisab se sort karti hai taaki chat seedhi dikhe.
    @Query("SELECT m FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp ASC")
    List<Message> findChatHistory(User user1, User user2);
}