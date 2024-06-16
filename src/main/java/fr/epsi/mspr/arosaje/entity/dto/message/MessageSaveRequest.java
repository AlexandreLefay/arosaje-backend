package fr.epsi.mspr.arosaje.entity.dto.message;

import fr.epsi.mspr.arosaje.entity.dto.message.validation.MandatoryMessageId;
import fr.epsi.mspr.arosaje.entity.dto.message.validation.OptionalMessageId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "aMessageSaveRequest", setterPrefix = "set")
public class MessageSaveRequest {

    @Null(groups = { OptionalMessageId.class })
    @NotNull(groups = { MandatoryMessageId.class })
    private Long id;

    private Long guardianshipId;

    private Long receiverId;

    private Long senderId;

    private String content;

}
