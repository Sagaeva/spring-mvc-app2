package be.sagaeva.springcourse.dao;

import be.sagaeva.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 24, "bob@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 24, "mike@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 24, "katy@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Sagaeva", 24, "sagaeva@gmail.com"));
    }

    public List<Person> index(){


        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id)
                .findAny().orElse(null);

    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);

    }

    public void update(int id, Person upodatePerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(upodatePerson.getName());
        personToBeUpdated.setAge(upodatePerson.getAge());
        personToBeUpdated.setEmail(upodatePerson.getEmail());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }

    @Override
    public String toString() {
        return "PersonDAO{" +
                "people=" + people +
                '}';
    }
}
