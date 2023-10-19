package com.example.animalwarchatting;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// ## 2
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Greeting {
    @Id
    private String name;
    private String message;
}
