package ru.goose.art.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.goose.art.dao.PersonDAO;
import ru.goose.art.models.Person;

@Controller
public class MainController {
    @Autowired
    PersonDAO personDAO;

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @PostMapping("/")
    public String call(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/";
    }
}
