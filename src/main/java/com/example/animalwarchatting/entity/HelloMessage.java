package com.example.animalwarchatting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


// ## 1
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloMessage {
    private UUID uuid;
    private String nickname;
    private String content;
}
