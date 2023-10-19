package com.example.animalwarchatting.Repository;

import com.example.animalwarchatting.Entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    Greeting save(Greeting greeting);

}
