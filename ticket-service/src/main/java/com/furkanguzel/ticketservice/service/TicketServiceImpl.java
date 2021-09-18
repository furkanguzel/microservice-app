package com.furkanguzel.ticketservice.service;

import com.furkanguzel.client.AccountServiceClient;
import com.furkanguzel.client.contractdto.AccountDto;
import com.furkanguzel.ticketservice.dto.TicketDto;
import com.furkanguzel.ticketservice.model.PriorityType;
import com.furkanguzel.ticketservice.model.Ticket;
import com.furkanguzel.ticketservice.model.TicketStatus;
import com.furkanguzel.ticketservice.model.elasticsearch.TicketModel;
import com.furkanguzel.ticketservice.repository.TicketRepository;
import com.furkanguzel.ticketservice.repository.elasticsearch.TicketElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketElasticRepository ticketElasticRepository;
    private final TicketRepository ticketRepository;
    private final TicketNotificationService ticketNotificationService;
    private final AccountServiceClient accountServiceClient;

    @Override
    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        //Ticket Entity
        Ticket ticket = new Ticket();
        //TODO validate from Account Api
        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());

        if (ticketDto.getDescription() == null)
            throw new IllegalArgumentException("Description cannot be empty!");

        ticket.setDescription((ticketDto.getDescription()));
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket.setAssignee(accountDtoResponseEntity.getBody().getId());

        //Save MySQL
        ticket = ticketRepository.save(ticket);

        //create TicketModel
        TicketModel ticketModel = TicketModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                .id(ticket.getId())
                .assignee(accountDtoResponseEntity.getBody().getNameAndSurname())
                .priorityType(ticket.getPriorityType().getLabel())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .ticketDate(ticket.getTicketDate()).build();

        //Save Elastic
        ticketElasticRepository.save(ticketModel);

        // return Object
        ticketDto.setId(ticket.getId());

        // Write notification to the queue.
        ticketNotificationService.sendToQueue(ticket);
        return ticketDto;
    }

    @Override
    public TicketDto update(String id, TicketDto ticketDto) {
        return null;
    }

    @Override
    public TicketDto getById(String ticketId) {
        return null;
    }

    @Override
    public Page<TicketDto> getPagination(Pageable pageable) {
        return null;
    }
}
