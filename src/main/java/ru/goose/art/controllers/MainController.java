package ru.goose.art.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.goose.art.dao.AdminDAO;
import ru.goose.art.dao.PersonDAO;
import ru.goose.art.dao.StudentDAO;
import ru.goose.art.models.Admin;
import ru.goose.art.models.Person;
import ru.goose.art.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {
    @Autowired
    PersonDAO personDAO;
    @Autowired
    AdminDAO adminDAO;
    @Autowired
    StudentDAO studentDAO;
    public boolean isAuth = false;
    public String name;
    public List<Person> applications;

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
    public String panelAdmin(@ModelAttribute("person") Admin admin){
        name = adminDAO.adminLogin(admin);
        if (!Objects.equals(name, "")){
            isAuth = true;
            return "redirect:/adminpanel";
        }
        return "redirect:/admin";
    }
    @GetMapping("/adminpanel")
    public String panel(Model model){
        if (isAuth){
            model.addAttribute("name", name);
            return "adminPanel";
        }
        return "redirect:/";
    }

    @GetMapping("/applications")
    public String applications(Model model){
        if (isAuth) {
            applications = personDAO.getAllData();
            model.addAttribute("applications", applications);
            return "applications";
        }
        return "redirect:/";
    }

    @PostMapping("/addStudents")
    public String addStudent(@ModelAttribute("student") Student student){
        int i = 0;
        /*System.out.println(student.getName());
        System.out.println(student.getStatus());*/
        String[] status = student.getStatus().split(",");
        if (isAuth){
            List<Student> students = new ArrayList<Student>();
            for (Person person : applications){
                if (Objects.equals(status[i], "yes")){
                    students.add(new Student(person.getName(),
                            person.getNumber(),
                            0,
                            "net", "yes"));
                }
                i++;
            }
            studentDAO.addStudents(students);
            return "redirect:/applications";
        }
        return "redirect:/";
    }
}
