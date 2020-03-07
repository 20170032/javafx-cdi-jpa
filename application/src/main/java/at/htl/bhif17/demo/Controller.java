package at.htl.bhif17.demo;

import javafx.event.ActionEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Random;

@ApplicationScoped
@Transactional
public class Controller {
    Random random;
    @Inject PersonDao dao;

    public void initialize() {
        random = new Random();
        load();
    }
    void load() {
        var persons = dao.getAll();
        persons.stream().forEach(System.out::println);
    }
    public void click(ActionEvent __) {
        var matNr = String.format("%05d", random.nextInt(99999));
        var person = Person.builder().firstName("Engelbert").lastName("Breitfuß").matNr(matNr).build();
        dao.save(person);
        System.out.println("------");
        load();
    }
}