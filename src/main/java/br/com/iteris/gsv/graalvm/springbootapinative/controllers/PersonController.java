package br.com.iteris.gsv.graalvm.springbootapinative.controllers;

import br.com.iteris.gsv.graalvm.springbootapinative.entities.Person;
import br.com.iteris.gsv.graalvm.springbootapinative.repositories.PersonRepository;
import br.com.iteris.gsv.graalvm.springbootapinative.requests.PersonRequest;
import br.com.iteris.gsv.graalvm.springbootapinative.responses.PersonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> listPeople(Pageable pageable) {
        return ResponseEntity.ok(repository.findAll(pageable).map(PersonResponse::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPersonByID(@PathVariable("id") Long id) {
        return repository.findById(id).map(PersonResponse::new)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody PersonRequest request) {
        Person save = repository.save(request.asNewPerson());
        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonResponse(save));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPerson(@PathVariable Long id, @RequestBody PersonRequest request) {
        Person save = repository.save(request.asUpdatedPerson(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonResponse(save));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removePerson(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
