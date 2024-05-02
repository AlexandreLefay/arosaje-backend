package fr.epsi.mspr.arosaje.service;


import fr.epsi.mspr.arosaje.entity.Guardianship;
import fr.epsi.mspr.arosaje.entity.Message;
import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.message.MessageDTO;
import fr.epsi.mspr.arosaje.entity.dto.message.MessageSaveRequest;
import fr.epsi.mspr.arosaje.entity.mapper.MessageMapper;
import fr.epsi.mspr.arosaje.repository.GuardianshipRepository;
import fr.epsi.mspr.arosaje.repository.MessageRepository;
import fr.epsi.mspr.arosaje.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Service for handling message data operations.
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    private final UserRepository userRepository;

    private final GuardianshipRepository guardianshipRepository;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper, UserRepository userRepository, GuardianshipRepository guardianshipRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.userRepository = userRepository;
        this.guardianshipRepository = guardianshipRepository;
    }

    /**
     * Retrieve all users.
     *
     * @return a list of all users.
     */
    public List<MessageDTO> findAll() {
        List<Message> messages = messageRepository.findAll();

        return messages.stream()
                .map(messageMapper::messagetoMessageDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Retrieve all messages for a specific user based on the sender id.
     *
     * @param senderId The ID of the sender to retrieve messages for.
     * @return a list of messages for the specified user.
     */
    public List<MessageDTO> findBySenderId(Long senderId) {
        List<Message> messages = messageRepository.findBySenderId(senderId);

        return messages.stream()
                .map(messageMapper::messagetoMessageDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Retrieve all messages for specific user based on the receiver id.
     *
     * @param receiverId The ID of the receiver to retrieve messages for.
     * @return a list of messages for the specified user.
     */
    public List<MessageDTO> findByReceiverId(Long receiverId) {
        List<Message> messages = messageRepository.findByReceiverId(receiverId);

        return messages.stream()
                .map(messageMapper::messagetoMessageDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Create a new message.
     *
     * @param messageDTO The message to create.
     * @return the created message.
     */
    public MessageDTO createMessage(MessageDTO messageDTO) {
        Message message = messageMapper.messageDTOtoMessageEntity(messageDTO);

        User sender = userRepository.findById(messageDTO.getSenderId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        User receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Guardianship guardianship = guardianshipRepository.findById(messageDTO.getGuardianshipId())
                .orElseThrow(() -> new EntityNotFoundException("Guardianship not found"));

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setGuardianship(guardianship);
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());
        return messageMapper.messagetoMessageDTO(messageRepository.save(message));
    }

    /**
     * Update an existing message.
     *
     * @param messageSaveRequest The MessageDTO containing the updated information for the message.
     * @return the updated message.
     */
    public MessageDTO updateMessage(MessageSaveRequest messageSaveRequest, Long messageId) {

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new EntityNotFoundException("Message not found"));

        messageMapper.updateMessageFromMessageSaveRequest(messageSaveRequest, message);

        return messageMapper.messagetoMessageDTO(messageRepository.save(message));
    }

    /**
     * Delete a message.
     *
     * @param id The ID of the message to delete.
     */
    public void deleteMessage(Long id) {
        if (!messageRepository.existsById(id)) {
            throw new EntityNotFoundException("Message not found");
        }
        messageRepository.deleteById(id);
    }

}
