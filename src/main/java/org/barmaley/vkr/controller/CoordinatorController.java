package org.barmaley.vkr.controller;


import org.apache.log4j.Logger;
import org.barmaley.vkr.autentication.CustomUser;
import org.barmaley.vkr.domain.*;
import org.barmaley.vkr.dto.TicketDTO;
import org.barmaley.vkr.service.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CoordinatorController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name = "ticketService")
    private TicketService ticketService;

    @Resource(name = "usersService")
    private UsersService usersService;

    @Resource(name = "coordinatorRightsService")
    private CoordinatorRightsService coordinatorRightsService;

    @GetMapping(value = "/coordinator")
    public String getCoordinatorPage(ModelMap model) {

        CustomUser principal = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = usersService.getById(principal.getId());
        List<CoordinatorRights> coordinatorRightsList = coordinatorRightsService.getCoordinatorRights(principal.getId());
        List<Ticket> ticketsNew = new ArrayList<>();
        List<TicketDTO> ticketNewDTOList = new ArrayList<>();
        List<Ticket> ticketsCheck = new ArrayList<>();
        List<TicketDTO> ticketInCheckDTOList = new ArrayList<>();
        Integer countTicketsNew = 0;
        Integer countTicketsInCheck = 0;
        if(!coordinatorRightsList.isEmpty()){
            for(CoordinatorRights coordinatorRights: coordinatorRightsList){
                List<Ticket> newTicketList = ticketService.getAllTicketForCoordinator(coordinatorRights.getGroupNum(), 2);
                List<Ticket> ticketsCheckList = ticketService.getAllTicketForCoordinator(coordinatorRights.getGroupNum(), 3);
                ticketsNew.addAll(newTicketList);
                ticketsCheck.addAll(ticketsCheckList);
            }
        }
        for (Ticket ticket: ticketsNew){
            countTicketsNew ++;
            TicketDTO dto = new TicketDTO(ticket.getId(), ticket.getGroupNum(), ticket.getUser().getFirstName(),
                    ticket.getUser().getSecondName(), ticket.getUser().getSurname(), ticket.getTitle(), ticket.getDocumentType().getName(),
                    ticket.getTypeOfUse().getName(), ticket.getStatus().getName());
            ticketNewDTOList.add(dto);
        }
        for (Ticket ticket: ticketsCheck){
            countTicketsInCheck ++;
            TicketDTO  dto = new TicketDTO(ticket.getId(), ticket.getGroupNum(), ticket.getUser().getFirstName(),
                    ticket.getUser().getSecondName(), ticket.getUser().getSurname(), ticket.getTitle(), ticket.getDocumentType().getName(),
                    ticket.getTypeOfUse().getName(), ticket.getStatus().getName());
            ticketInCheckDTOList.add(dto);
        }
        model.addAttribute("ticketsNew", ticketNewDTOList);
        model.addAttribute("countTicketsNew", countTicketsNew);
        model.addAttribute("ticketsInCheck", ticketInCheckDTOList);
        model.addAttribute("countTicketsInCheck", countTicketsInCheck);
        model.addAttribute("user", user);
        return ("coordinatorPage");
    }
}