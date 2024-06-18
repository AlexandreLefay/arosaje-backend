//package fr.epsi.mspr.arosaje.controller;
//
//
//import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCommentCreationDTO;
//import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCommentDTO;
//import fr.epsi.mspr.arosaje.service.TicketCommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * REST Controller for managing ticket comments.
// * Provides endpoints for creating, retrieving, updating, and deleting ticket comments.
// */
//@RestController
//@CrossOrigin(origins = "http://localhost:19006")
//@RequestMapping("/api/tickets/{ticketId}/comments")
//public class TicketCommentController {
//
//    @Autowired
//    private TicketCommentService ticketCommentService;
//
//    /**
//     * Creates a new comment for a specified ticket.
//     *
//     * @param ticketId   The ID of the ticket to which the comment belongs.
//     * @param commentDTO The DTO containing the comment creation information.
//     * @return ResponseEntity with the created TicketCommentDTO.
//     */
//    @PostMapping
//    public ResponseEntity<TicketCommentDTO> createComment(@PathVariable Long ticketId, @RequestBody TicketCommentCreationDTO commentDTO) {
//        TicketCommentDTO newComment = ticketCommentService.createComment(commentDTO, ticketId);
//        return ResponseEntity.ok(newComment);
//    }
//
//    /**
//     * Retrieves a specific comment by its ID.
//     *
//     * @param commentId The ID of the comment to retrieve.
//     * @return ResponseEntity with the TicketCommentDTO.
//     */
//    @GetMapping("/{commentId}")
//    public ResponseEntity<TicketCommentDTO> getCommentById(@PathVariable Long commentId, @PathVariable Long ticketId) {
//        TicketCommentDTO comment = ticketCommentService.getCommentById(commentId, ticketId);
//        return ResponseEntity.ok(comment);
//    }
//
//    /**
//     * Retrieves all comments associated with a specific ticket.
//     *
//     * @param ticketId The ID of the ticket for which comments are to be retrieved.
//     * @return ResponseEntity with a list of TicketCommentDTOs.
//     */
//    @GetMapping
//    public ResponseEntity<List<TicketCommentDTO>> getAllCommentsByTicket(@PathVariable Long ticketId) {
//        List<TicketCommentDTO> comments = ticketCommentService.getAllCommentsByTicket(ticketId);
//        return ResponseEntity.ok(comments);
//    }
//
//    /**
//     * Updates an existing comment.
//     *
//     * @param commentId  The ID of the comment to update.
//     * @param commentDTO The DTO containing the updated information for the comment.
//     * @return ResponseEntity with the updated TicketCommentDTO.
//     */
//    @PutMapping("/{commentId}")
//    public ResponseEntity<TicketCommentDTO> updateComment(@PathVariable Long ticketId, @PathVariable Long commentId,
//                                                          @RequestBody TicketCommentCreationDTO commentDTO) {
//        TicketCommentDTO updatedComment = ticketCommentService.updateComment(ticketId, commentId, commentDTO);
//        return ResponseEntity.ok(updatedComment);
//    }
//
//    /**
//     * Deletes a comment by its ID.
//     *
//     * @param commentId The ID of the comment to delete.
//     * @return ResponseEntity indicating the operation's success status.
//     */
//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<?> deleteComment(@PathVariable Long ticketId, @PathVariable Long commentId) {
//        ticketCommentService.deleteComment(ticketId, commentId);
//        return ResponseEntity.ok().build();
//    }
//}