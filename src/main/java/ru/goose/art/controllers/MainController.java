package ru.goose.art.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.goose.art.bot.Bot;
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
    public List<Person> applications; // заявки
    public List<Student> students_all; // все студенты
    List<String> options = new ArrayList<String>(); // Профессии

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }
    // Оставление заявки
    @PostMapping("/")
    public String call(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        try {
            Bot bot = new Bot();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
            bot.sendAds(person.getName() + " " + person.getNumber());

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
        System.out.println(student.getName());
        String[] names = student.getName().split(",");
        String[] status = student.getStatus().split(",");
        String[] number = student.getNumber().split(",");
        if (isAuth){
            List<Student> students = new ArrayList<Student>();
            for (int i = 0; i < names.length; i++){
                if (!Objects.equals(status[i], "no")){
                    students.add(new Student(names[i],
                            number[i],
                            "0",
                            "2D-Художник", status[i]));
                }
            }
            studentDAO.addStudents(students);
            return "redirect:/applications";
        }
        return "redirect:/";
    }
    // Страница со студентами
    @GetMapping("/students")
    public String Students(Model model){
        List<String> options = new ArrayList<String>(); // Профессии
        options.add("2D-Художник");
        options.add("3D-Художник");
        options.add("Игровая графика");
        options.add("Веб-дизайн");
        List<String> options1 = new ArrayList<String>(); // Профессии
        options1.add("0");
        options1.add("1");
        options1.add("2");
        options1.add("3");
        if (isAuth){
            students_all = studentDAO.getAllData();
            model.addAttribute("students", students_all);
            model.addAttribute("options", options);
            model.addAttribute("options1", options1);
            return "studentsEdit";
        }
        return "redirect:/";
    }
    // Изменение Студента
    @PostMapping("/editStudents")
    public String editStudents(@ModelAttribute("student") Student student){
        if (isAuth){
            String[] names = student.getName().split(",");
            String[] number = student.getNumber().split(",");
            String[] special = student.getSpecial().split(",");
            String[] groupe = student.getGroupe().split(",");
            List<Student> students = new ArrayList<Student>();
            for (int i = 0; i < names.length; i++){
                students.add(new Student(names[i],
                            number[i],
                            groupe[i],
                            special[i], "yes"));
            }
            studentDAO.EditStudents(students);
            return "redirect:/students";
        }
        return "redirect:/";
    }
}
