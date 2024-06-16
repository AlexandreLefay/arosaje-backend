package fr.epsi.mspr.arosaje.entity.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "aMessageDTO", setterPrefix = "set")
public class MessageDTO {

    private Long id;

    private Long guardianshipId;

    private Long receiverId;

    private Long senderId;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
