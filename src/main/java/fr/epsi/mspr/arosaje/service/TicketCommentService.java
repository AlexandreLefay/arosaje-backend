package fr.epsi.mspr.arosaje.service;

import fr.epsi.mspr.arosaje.entity.Ticket;
import fr.epsi.mspr.arosaje.entity.TicketComment;
import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCommentCreationDTO;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCommentDTO;
import fr.epsi.mspr.arosaje.entity.mapper.TicketCommentMapper;
import fr.epsi.mspr.arosaje.repository.TicketCommentRepository;
import fr.epsi.mspr.arosaje.repository.TicketRepository;
import fr.epsi.mspr.arosaje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling operations related to TicketComment data.
 */
@Service
public class TicketCommentService {

    @Autowired
    private TicketCommentRepository ticketCommentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Retrieves all comments associated with a specific ticket.
     *
     * @param ticketId The ID of the ticket for which comments are to be retrieved.
     * @return A list of TicketCommentDTOs representing the comments for the specified ticket.
     */
    public List<TicketCommentDTO> getAllCommentsByTicket(Long ticketId) {
        List<TicketComment> comments = ticketCommentRepository.findByTicketId(ticketId);
        return comments.stream()
                .map(TicketCommentMapper.INSTANCE::ticketCommentToTicketCommentDTO)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new comment for a ticket.
     *
     * @param commentDTO The DTO containing the comment creation information.
     * @return The created TicketCommentDTO.
     */
    public TicketCommentDTO createComment(TicketCommentCreationDTO commentDTO, Long ticketId) {
        TicketComment comment = TicketCommentMapper.INSTANCE.ticketCommentCreationDTOToTicketComment(commentDTO);

        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        comment.setUser(user);
        comment.setTicket(ticket);

        TicketComment savedComment = ticketCommentRepository.save(comment);
        return TicketCommentMapper.INSTANCE.ticketCommentToTicketCommentDTO(savedComment);
    }

    /**
     * Retrieves a specific comment by its ID.
     *
     * @param commentId The ID of the comment to retrieve.
     * @param ticketId The ID of the ticket to which the comment belongs.
     * @return The TicketCommentDTO representing the retrieved comment.
     * @throws RuntimeException if the comment is not found.
     */
    public TicketCommentDTO getCommentById(Long commentId, Long ticketId) {

        TicketComment comment = ticketCommentRepository.findCommentByTicketIdAndCommentId(ticketId, commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        return TicketCommentMapper.INSTANCE.ticketCommentToTicketCommentDTO(comment);
    }

    /**
     * Updates an existing comment.
     *
     * @param commentId  The ID of the comment to update.
     * @param commentDTO The DTO containing the updated information for the comment.
     * @return The updated TicketCommentDTO.
     * @throws RuntimeException if the comment is not found.
     */
    public TicketCommentDTO updateComment(Long ticketId, Long commentId, TicketCommentCreationDTO commentDTO) {
        TicketComment existingComment = ticketCommentRepository.findCommentByTicketIdAndCommentId(ticketId, commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        existingComment.setComment(commentDTO.getComment());

        TicketComment updatedComment = ticketCommentRepository.save(existingComment);
        return TicketCommentMapper.INSTANCE.ticketCommentToTicketCommentDTO(updatedComment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param ticketId The ID of the ticket to which the comment belongs.
     * @param commentId The ID of the comment to delete.
     * @throws RuntimeException if the comment is not found.
     */
    public void deleteComment(Long ticketId, Long commentId) {
        if (!ticketCommentRepository.existsCommentByTicketIdAndCommentId(ticketId, commentId)) {
            throw new RuntimeException("Comment not found");
        }
        ticketCommentRepository.deleteCommentByTicketIdAndCommentId(ticketId, commentId);
    }
}