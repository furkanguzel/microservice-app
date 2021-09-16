package com.furkanguzel.ticketservice.repository.elasticsearch;

import com.furkanguzel.ticketservice.model.elasticsearch.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticRepository extends ElasticsearchRepository<TicketModel, String> {
}
