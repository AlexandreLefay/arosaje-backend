package fr.epsi.mspr.arosaje.repository;

import fr.epsi.mspr.arosaje.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderId(Long senderId);

    List<Message> findByReceiverId(Long receiverId);
}
