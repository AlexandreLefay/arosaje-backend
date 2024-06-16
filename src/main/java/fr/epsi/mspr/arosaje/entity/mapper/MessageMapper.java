package fr.epsi.mspr.arosaje.entity.mapper;

import fr.epsi.mspr.arosaje.entity.Message;
import fr.epsi.mspr.arosaje.entity.dto.message.MessageDTO;
import fr.epsi.mspr.arosaje.entity.dto.message.MessageSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    /**
     * Convert a message entity to a message dto
     */
    @Mapping(source = "sender.id", target = "senderId")
    @Mapping(source = "receiver.id", target = "receiverId")
    @Mapping(source = "guardianship.id", target = "guardianshipId")
    MessageDTO messagetoMessageDTO(Message message);

    /**
     * Convert a message dto to a message entity
     */
    Message messageDTOtoMessageEntity(MessageDTO messageDTO);

    /**
     * Overwrite a message entity with a messageSaveRequest
     */
    void updateMessageFromMessageSaveRequest(MessageSaveRequest messageSaveRequest, @MappingTarget Message message);}
