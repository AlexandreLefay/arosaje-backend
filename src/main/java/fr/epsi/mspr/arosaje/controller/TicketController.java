package fr.epsi.mspr.arosaje.controller;


import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCreationDTO;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketResponseDTO;
import fr.epsi.mspr.arosaje.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing tickets.
 * Provides endpoints for creating, retrieving, updating, and deleting tickets.
 */
@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Creates a new ticket.
     *
     * @param ticketCreationDTO The TicketCreationDTO containing the new ticket's information.
     * @return ResponseEntity containing the created TicketResponseDTO.
     */
    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody TicketCreationDTO ticketCreationDTO) {
        TicketResponseDTO newTicket = ticketService.createTicket(ticketCreationDTO);
        return ResponseEntity.ok(newTicket);
    }


    /**
     * Retrieves all tickets.
     *
     * @return ResponseEntity containing a list of TicketResponseDTOs for each ticket.
     */
    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getAllTickets() {
        List<TicketResponseDTO> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    /**
     * Retrieves all tickets for a specific user.
     *
     * @param userId The ID of the user to retrieve tickets for.
     * @return ResponseEntity containing a list of TicketResponseDTOs for each ticket.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketResponseDTO>> getAllTicketsForUser(@PathVariable Long userId) {
        List<TicketResponseDTO> tickets = ticketService.getAllTicketsByUserId(userId);
        return ResponseEntity.ok(tickets);
    }

    /**
     * Retrieves a specific ticket by its ID.
     *
     * @param id The ID of the ticket to retrieve.
     * @return ResponseEntity containing the TicketResponseDTO for the specified ticket.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long id) {
        TicketResponseDTO ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    /**
     * Updates an existing ticket.
     *
     * @param id                The ID of the ticket to update.
     * @param ticketCreationDTO The TicketCreationDTO containing the updated information for the ticket.
     * @return ResponseEntity containing the updated TicketResponseDTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long id, @RequestBody TicketCreationDTO ticketCreationDTO) {
        TicketResponseDTO updatedTicket = ticketService.updateTicket(id, ticketCreationDTO);
        return ResponseEntity.ok(updatedTicket);
    }

    /**
     * Deletes a ticket identified by its ID.
     *
     * @param id The ID of the ticket to delete.
     * @return ResponseEntity indicating the operation's success status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok().build();
    }
}
