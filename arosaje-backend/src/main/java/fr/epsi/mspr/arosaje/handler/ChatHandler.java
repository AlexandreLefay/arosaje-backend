package fr.epsi.mspr.arosaje.handler;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import fr.epsi.mspr.arosaje.entity.dto.message.MessageDTO;
import fr.epsi.mspr.arosaje.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChatHandler is responsible for handling WebSocket connections and messages
 * between users.
 */
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private final MessageService messageService;
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    /**
     * Constructs a new ChatHandler with the specified MessageService.
     *
     * @param messageService the service used to handle message-related operations
     */
    public ChatHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Invoked after WebSocket connection is established.
     *
     * @param session the established WebSocket session
     * @throws Exception if an error occurs
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String query = session.getUri().getQuery();
        String userId = query != null ? query.substring(query.indexOf("userId=") + 7) : null;

        if (userId != null && !userId.isEmpty()) {
            sessions.put(userId, session);
            log.info("Session added for user: {}", userId);
        } else {
            log.warn("A session was established without a valid user ID.");
            session.close(CloseStatus.BAD_DATA.withReason("User ID required"));
        }
    }

    /**
     * Handles incoming text messages.
     *
     * @param session the WebSocket session
     * @param message the incoming text message
     * @throws Exception if an error occurs
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessagePayload payload = parseMessage(message.getPayload());
        if (payload == null) {
            log.error("Received an invalid message format");
            session.close(CloseStatus.BAD_DATA.withReason("Invalid message format"));
            return;
        }

        Long senderId = payload.getSenderId();
        Long receiverId = payload.getReceiverId();
        String content = payload.getContent();

        log.info("Received message from {} to {}", senderId, receiverId);

        // Logique pour envoyer le message au destinataire
        WebSocketSession receiverSession = sessions.get(receiverId.toString());
        if (receiverSession != null && receiverSession.isOpen()) {
            String jsonMessage = new Gson().toJson(payload);
            receiverSession.sendMessage(new TextMessage(jsonMessage));
            log.info("Sending message from {} to {}", senderId, receiverId);
            saveMessageToDatabase(payload);
        } else {
            log.info("Failed to send message from {} to {}: Receiver session not found", senderId, receiverId);
            // Enregistrer le message dans la base de données car le destinataire n'est pas connecté
            saveMessageToDatabase(payload);
        }

        // Envoyer également le message de retour à l'expéditeur si celui-ci est connecté sur plusieurs clients
        WebSocketSession senderSession = sessions.get(senderId.toString());
        if (senderSession != null && !senderSession.getId().equals(session.getId())) {
            senderSession.sendMessage(new TextMessage(content));
            log.info("Echoing back message to sender {}", senderId);
        }
    }

    /**
     * Invoked after WebSocket connection is closed.
     *
     * @param session the closed WebSocket session
     * @param status  the status of the closed connection
     * @throws Exception if an error occurs
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = session.getUri().getQuery().substring(session.getUri().getQuery().indexOf("userId=") + 7);
        if (userId != null) {
            sessions.remove(userId);
            log.info("Session removed for user: {}", userId);
        }
    }

    /**
     * Parses a JSON string into a MessagePayload object.
     *
     * @param messageJson the JSON string to be parsed
     * @return the parsed MessagePayload object or null if parsing fails
     */
    private MessagePayload parseMessage(String messageJson) {
        try {
            return new Gson().fromJson(messageJson, MessagePayload.class);
        } catch (JsonSyntaxException e) {
            log.error("Error parsing message JSON: {}", e.getMessage());
            return null;
        }
    }

    /**
     * Saves a message to the database.
     *
     * @param payload the MessagePayload object to be saved
     */
    private void saveMessageToDatabase(MessagePayload payload) {
        try {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setSenderId(payload.getSenderId());
            messageDTO.setReceiverId(payload.getReceiverId());
            messageDTO.setContent(payload.getContent());
            // Utiliser l'ID de guardianship adéquat ici, selon ta logique métier
            messageDTO.setGuardianshipId(payload.getGuardianshipId());

            messageService.createMessage(messageDTO);
            log.info("Message from {} to {} saved in database", payload.getSenderId(), payload.getReceiverId());
        } catch (Exception e) {
            log.error("Failed to save message to database: {}", e.getMessage());
        }
    }
}
