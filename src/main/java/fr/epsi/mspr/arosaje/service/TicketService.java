package fr.epsi.mspr.arosaje.service;


import fr.epsi.mspr.arosaje.entity.Ticket;
import fr.epsi.mspr.arosaje.entity.User;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketCreationDTO;
import fr.epsi.mspr.arosaje.entity.dto.ticket.TicketResponseDTO;
import fr.epsi.mspr.arosaje.entity.mapper.TicketMapper;
import fr.epsi.mspr.arosaje.repository.TicketCommentRepository;
import fr.epsi.mspr.arosaje.repository.TicketRepository;
import fr.epsi.mspr.arosaje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.rmi.server.LogStream.log;

/**
 * Service for handling ticket data operations.
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketCommentRepository ticketCommentRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new ticket from the given TicketCreationDTO.
     *
     * @param ticketCreationDTO Data Transfer Object containing ticket creation information.
     * @return TicketResponseDTO representing the created ticket.
     */
    public TicketResponseDTO createTicket(TicketCreationDTO ticketCreationDTO) {
        Ticket ticket = TicketMapper.INSTANCE.ticketCreationDTOToTicket(ticketCreationDTO);

        User user = userRepository.findById(ticketCreationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ticket.setUser(user);
        ticket.setStatus("Ouvert");

        Ticket savedTicket = ticketRepository.save(ticket);
        return TicketMapper.INSTANCE.ticketToTicketResponseDTO(savedTicket);
    }

    /**
     * Retrieves all tickets.
     *
     * @return A list of TicketResponseDTOs for each ticket in the database.
     */
    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(TicketMapper.INSTANCE::ticketToTicketResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific ticket by its ID, including its comments.
     *
     * @param id The ID of the ticket to retrieve.
     * @return TicketResponseDTO representing the retrieved ticket.
     * @throws RuntimeException if the ticket is not found.
     */
    public TicketResponseDTO getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return TicketMapper.INSTANCE.ticketToTicketResponseDTO(ticket);
    }

    /**
     * Retrieves all tickets by user ID.
     * @param userId The ID of the user to retrieve tickets for.
     * @return A list of TicketResponseDTOs for each ticket in the database.
     */

    public List<TicketResponseDTO> getAllTicketsByUserId(Long userId) {
        return ticketRepository.findAllByUserId(userId).stream()
                .map(TicketMapper.INSTANCE::ticketToTicketResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing ticket with the information provided in the TicketCreationDTO.
     *
     * @param id                The ID of the ticket to update.
     * @param ticketCreationDTO Data Transfer Object containing updated ticket information.
     * @return TicketResponseDTO representing the updated ticket.
     * @throws RuntimeException if the ticket is not found.
     */
    public TicketResponseDTO updateTicket(Long id, TicketCreationDTO ticketCreationDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setTitle(ticketCreationDTO.getTitle());
        ticket.setDescription(ticketCreationDTO.getDescription());
        ticket.setUser(userRepository.findById(ticketCreationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));
        Ticket updatedTicket = ticketRepository.save(ticket);
        return TicketMapper.INSTANCE.ticketToTicketResponseDTO(updatedTicket);
    }

    /**
     * Deletes a ticket identified by its ID.
     *
     * @param id The ID of the ticket to delete.
     * @throws RuntimeException if the ticket is not found.
     */
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketCommentRepository.deleteCommentsByTicketId(id);
        ticketRepository.deleteById(id);
    }
}
