package fr.epsi.mspr.arosaje.repository;

import fr.epsi.mspr.arosaje.entity.TicketComment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TicketCommentRepository extends JpaRepository<TicketComment, Long> {
    /**
     * Finds all ticket comments based on the given ticket ID.
     *
     * @param ticketId The ID of the ticket.
     * @return A list of TicketComment entities.
     */
    List<TicketComment> findByTicketId(Long ticketId);

    /**
     * Find one ticket comment based on the given ticket ID and comment ID.
     * @param ticketId The ID of the ticket.
     * @param commentId The ID of the comment.
     * @return A list of TicketComment entities.
     *
     */
    @Query("SELECT tc FROM TicketComment tc WHERE tc.ticket.id = :ticketId AND tc.id = :commentId")
    Optional<TicketComment> findCommentByTicketIdAndCommentId(@Param("ticketId") Long ticketId, @Param("commentId") Long commentId);

    /**
     * Delete one ticket comment based on the given ticket ID and comment ID.
     * @param ticketId The ID of the ticket.
     * @param commentId The ID of the comment.
     */
    @Modifying
    @Query("DELETE FROM TicketComment tc WHERE tc.ticket.id = :ticketId AND tc.id = :commentId")
    void deleteCommentByTicketIdAndCommentId(@Param("ticketId") Long ticketId, @Param("commentId") Long commentId);

    @Modifying
    @Query("DELETE FROM TicketComment tc WHERE tc.ticket.id = :ticketId")
    void deleteCommentsByTicketId(@Param("ticketId") Long ticketId);


    /**
     * Check if one comment exists based on the given ticket ID and comment ID.
     * @param ticketId The ID of the ticket.
     * @param commentId The ID of the comment.
     * @return A boolean.
     */

    @Query("SELECT CASE WHEN COUNT(tc) > 0 THEN true ELSE false END FROM TicketComment tc WHERE tc.ticket.id = :ticketId AND tc.id = :commentId")
    boolean existsCommentByTicketIdAndCommentId(@Param("ticketId") Long ticketId, @Param("commentId") Long commentId);

}