package ru.goose.art.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.goose.art.dao.AdminDAO;
import ru.goose.art.dao.PersonDAO;
import ru.goose.art.models.Admin;
import ru.goose.art.models.Person;

import java.util.Objects;

@Controller
public class MainController {
    @Autowired
    PersonDAO personDAO;
    @Autowired
    AdminDAO adminDAO;

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
    // Оставление заявки
    @PostMapping("/")
    public String call(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminLogin(@ModelAttribute("person") Person person){
        return "login";
    }
    // Вход в панель админа
    @PostMapping("/admin")
    public String panelAdmin(@ModelAttribute("person") Admin admin, Model model){
        String name = adminDAO.adminLogin(admin);
        if (!Objects.equals(name, "")){
            model.addAttribute("name", name);
            return "adminPanel";
        }
        return "redirect:/admin";
    }
}
