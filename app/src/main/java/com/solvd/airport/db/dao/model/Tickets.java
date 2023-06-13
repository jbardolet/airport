package com.solvd.airport.db.dao.model;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "tickets")
public class Tickets {

    @XmlElement(name="ticket", type= Ticket.class)
    private List<Ticket> ticketList = new ArrayList<>();

    public Tickets(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public Tickets() {
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
