package be.sagaeva.springcourse.controllers;

import be.sagaeva.springcourse.dao.PersonDAO;
import be.sagaeva.springcourse.models.Person;

import be.sagaeva.springcourse.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }



    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        System.out.println(person);
        return "people/new";
    }



    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/new";}
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping( "/{id}")
    public String update(@ModelAttribute("person")
                         @Valid Person person, @PathVariable("id") int id,
                         BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/edit;";}
        personDAO.update(id, person);
        return "redirect:/people";
    }
    // остановилась на уроке 45
    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
