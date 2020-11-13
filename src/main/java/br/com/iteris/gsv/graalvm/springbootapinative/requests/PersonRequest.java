package br.com.iteris.gsv.graalvm.springbootapinative.requests;

import br.com.iteris.gsv.graalvm.springbootapinative.entities.Person;

import java.time.ZonedDateTime;

public class PersonRequest {

    private String name;
    private Integer age;

    public Person asNewPerson() {
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        person.setCreatedDate(ZonedDateTime.now());

        return person;
    }

    public Person asUpdatedPerson(Long id) {
        Person person = new Person();
        person.setId(id);
        person.setAge(age);
        person.setName(name);
        person.setModifiedDate(ZonedDateTime.now());

        return person;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
