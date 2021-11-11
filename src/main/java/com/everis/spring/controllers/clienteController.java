package com.everis.spring.controllers;

import com.everis.spring.repository.EverisCustomer;
import com.everis.spring.services.EverisCustomerManagementServiceI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class clienteController {

    @Autowired
    private EverisCustomerManagementServiceI customerService;

    public static String TEST_VIEW = "index";

    //Ver Index
    @GetMapping("/index")
    public String showindex() {
        return TEST_VIEW;
    }

    
    public static String client_VIEW = "showClient";

    @GetMapping("/showClientView")
    public String showClientView(Model model) {
        List<EverisCustomer> showClient = customerService.searchAll();

        model.addAttribute("showClient", showClient);
        model.addAttribute("btnDropClient", false); // para controlar la visibilidad del botón en el html

        return client_VIEW;
    }

    @PostMapping("/eliminarCliente")
    public String deleteClient(@RequestParam String id, Model model) {

        // Eliminación de cliente
        customerService.deleteById(Long.valueOf(id));

        return "redirect:showClientView";
    }

    public static String NEW_CLIENT_VIEW = "newClient";

    @GetMapping("/newClientView")
    public String showNewClient() {
        return NEW_CLIENT_VIEW;
    }

    @PostMapping("/anadirCliente")
    public String anadirCliente(@ModelAttribute("newClient") EverisCustomer newClient) {

        customerService.insertNewCustomer(newClient);

        return "redirect:showClientView";
    }

}
