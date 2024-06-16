package fr.epsi.mspr.arosaje.controller;

import fr.epsi.mspr.arosaje.entity.dto.message.MessageDTO;
import fr.epsi.mspr.arosaje.entity.dto.message.MessageSaveRequest;
import fr.epsi.mspr.arosaje.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST Controller for managing messages.
 */
@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * GET /api/messages : get all the messages.
     *
     * @return the list of messages.
     */
    @GetMapping
    public List<MessageDTO> getAllMessages() {
        return messageService.findAll();
    }

    /**
     * Retrieves messages by sender user id.
     *
     * @param senderId the user who send the message
     * @return the list of messages.
     */
    @GetMapping("/senders/{senderId}")
    public List<MessageDTO> getMessagesBySenderId(@PathVariable Long senderId) {
        return messageService.findBySenderId(senderId);
    }

    /**
     * Retrieves messages by receiver user id.
     *
     * @param receiverId the user who receive the message
     * @return the list of messages.
     */
    @GetMapping("/receivers/{receiverId}")
    public List<MessageDTO> getMessagesByReceiverId(@PathVariable Long receiverId) {
        return messageService.findByReceiverId(receiverId);
    }

    /**
     * Create a new message.
     *
     * @param messageDTO the message to create
     * @return the created message
     */
    @PostMapping
    public MessageDTO createMessage(@RequestBody MessageDTO messageDTO) {
        return messageService.createMessage(messageDTO);
    }

    /**
     * Update a message.
     *
     * @param messageSaveRequest the message to update
     * @return the updated message
     */
    @PutMapping("/{id}")
    public MessageDTO updateMessage(MessageSaveRequest messageSaveRequest, @PathVariable Long id) {
        return messageService.updateMessage(messageSaveRequest, id);
    }

    /**
     * Delete a message.
     *
     * @param id the message to delete
     */
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
