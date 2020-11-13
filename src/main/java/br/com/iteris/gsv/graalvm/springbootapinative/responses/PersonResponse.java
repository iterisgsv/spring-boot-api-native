package br.com.iteris.gsv.graalvm.springbootapinative.responses;

import br.com.iteris.gsv.graalvm.springbootapinative.entities.Person;

public class PersonResponse {
    private final Long id;
    private final String name;
    private final Integer age;

    public PersonResponse(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.age = person.getAge();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
