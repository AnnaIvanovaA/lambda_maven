package com.jet.variableView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository {
    private List<Person> persons;

    public PersonRepository() {
        this.persons = new ArrayList<>();
        initPersons();
    }

    private void initPersons() {
        persons.add(new Person("John Doe", 30, LocalDate.of(1993, 1, 15), "john.doe@example.com"));
        persons.add(new Person("Jane Smith", 25, LocalDate.of(1998, 6, 20), "jane.smith@example.com"));
        persons.add(new Person("Alice Johnson", 32, LocalDate.of(1991, 8, 25), "alice.johnson@example.com"));
        persons.add(new Person("Bob Brown", 29, LocalDate.of(1994, 12, 5), "bob.brown@example.com"));
        persons.add(new Person("Charlie Davis", 35, LocalDate.of(1988, 3, 16), "charlie.davis@example.com"));
        persons.add(new Person("Diana Evans", 28, LocalDate.of(1995, 9, 30), "diana.evans@example.com"));
        persons.add(new Person("Edward Fox", 40, LocalDate.of(1983, 11, 11), "edward.fox@example.com"));
        persons.add(new Person("Fiona Green", 27, LocalDate.of(1996, 4, 18), "fiona.green@example.com"));
        persons.add(new Person("George Harris", 31, LocalDate.of(1992, 2, 22), "george.harris@example.com"));
        persons.add(new Person("Hannah Wilson", 26, LocalDate.of(1997, 5, 25), "hannah.wilson@example.com"));
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public static void main(String[] args) {
        PersonRepository repository = new PersonRepository();

        // Additional person can be added like this:
        repository.addPerson(new Person("Ian Moore", 34, LocalDate.of(1989, 7, 11), "ian.moore@example.com"));

        for (Person person : repository.getPersons()) {
            System.out.println(person);
        }
        System.out.println();
    }
}