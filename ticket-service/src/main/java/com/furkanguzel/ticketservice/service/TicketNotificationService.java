package com.furkanguzel.ticketservice.service;

import com.furkanguzel.ticketservice.model.Ticket;

public interface TicketNotificationService {

     void sendToQueue(Ticket ticket);
}
