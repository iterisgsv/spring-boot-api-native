package br.com.iteris.gsv.graalvm.springbootapinative.repositories;

import br.com.iteris.gsv.graalvm.springbootapinative.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
