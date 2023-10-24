package com.example.animalwarchatting.entity;

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
