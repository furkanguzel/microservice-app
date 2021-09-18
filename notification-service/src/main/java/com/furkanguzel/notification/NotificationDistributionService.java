package com.furkanguzel.notification;

import com.furkanguzel.client.com.furkanguzel.messaging.TicketNotification;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class NotificationDistributionService {

    @StreamListener(Sink.INPUT)
    public void onNotification(TicketNotification ticketNotification){
        System.out.println("-------------------------------------------");
        System.out.println("Notification received. It is sent to users.");
        System.out.println("Notification -> " + ticketNotification.toString());
    }
}
