package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name= "new_user")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String name;

    @Column(length = 10, nullable = false)
    private String age;
}
