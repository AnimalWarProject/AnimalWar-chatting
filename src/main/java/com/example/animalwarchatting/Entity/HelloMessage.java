package com.example.animalwarchatting.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// ## 1
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage {
    private String name;
    private String content;
}
