package fr.epsi.mspr.arosaje.entity.mapper;

import fr.epsi.mspr.arosaje.entity.Address;
import fr.epsi.mspr.arosaje.entity.Photo;
import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.adresse.AddressDTO;
import fr.epsi.mspr.arosaje.entity.dto.photo.PhotoDto;
import fr.epsi.mspr.arosaje.entity.dto.user.UserDTO;
import fr.epsi.mspr.arosaje.entity.dto.user.UserSaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user The User entity to be converted.
     * @return The corresponding UserDTO.
     */
    @Mapping(source = "photo", target = "photo")
    UserDTO userToUserDTO(User user);


    /**
     * Converts an Address entity to an AddressDTO.
     *
     * @param address The Address entity to be converted.
     * @return The corresponding AddressDTO.
     */
    AddressDTO addressToAddressDTO(Address address);

    /**
     * Converts an AddressDTO to an Address entity.
     *
     * @param addressDTO The AddressDTO to be converted.
     * @return The corresponding Address entity.
     */
    Address addressDTOToAddress(AddressDTO addressDTO);

    /**
     * Converts a UserSaveRequest to a User entity.
     */
    User userSaveRequestToUser(UserSaveRequest userSaveRequest);

    /**
     * Updates a User entity with the data provided in a UserCreationDTO.
     *
     * @param userSaveRequest The DTO containing the new user data.
     * @param user            The User entity to be updated.
     */
    void updateUserFromUserSaveRequest(UserSaveRequest userSaveRequest, @MappingTarget User user);

    /**
     * Converts a Photo entity to a PhotoResponseDto.
     *
     * @param photo The Photo entity to be converted.
     * @return The corresponding PhotoResponseDto.
     */
    PhotoDto photoToPhotoResponseDto(Photo photo);


}

