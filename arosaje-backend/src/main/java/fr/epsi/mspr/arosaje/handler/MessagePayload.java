package fr.epsi.mspr.arosaje.handler;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the payload of a message in the system.
 */
@Getter
@Setter
public class MessagePayload {
    private Long senderId;
    private Long receiverId;
    private Long guardianshipId;
    private String content;

    /**
     * Constructs a new MessagePayload object with the specified sender ID, receiver ID, and content.
     *
     * @param senderId   the ID of the sender
     * @param receiverId the ID of the receiver
     * @param content    the content of the message
     */
    public MessagePayload(Long senderId, Long receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    /**
     * Converts a JSON string into a MessagePayload object.
     *
     * @param json the JSON string to be converted
     * @return the MessagePayload object represented by the JSON string
     */
    public static MessagePayload fromJson(String json) {
        // Utilisation de Gson pour désérialiser le JSON en objet MessagePayload
        return new Gson().fromJson(json, MessagePayload.class);
    }

    /**
     * Converts a MessagePayload object into a JSON string.
     *
     * @param payload the MessagePayload object to be converted
     * @return the JSON string representation of the MessagePayload object
     */
    public static String toJson(MessagePayload payload) {
        // Utilisation de Gson pour sérialiser l'objet MessagePayload en chaîne JSON
        return new Gson().toJson(payload);
    }
}
