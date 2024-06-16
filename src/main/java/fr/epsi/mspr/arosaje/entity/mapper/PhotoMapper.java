package fr.epsi.mspr.arosaje.entity.mapper;

import fr.epsi.mspr.arosaje.entity.Photo;
import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoDto;
import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "ticketCommentId", target = "ticketComment.id")
    Photo photoSaveRequestToPhoto(PhotoSaveRequest photoSaveRequest);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "ticketComment.id", target = "ticketCommentId")
    PhotoDto photoToPhotoDto(Photo photo);

    void updatePhotoFromPhotoSaveRequest(PhotoSaveRequest photoSaveRequest, @MappingTarget Photo photo);
}
