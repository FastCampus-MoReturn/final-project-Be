package com.fastcampus.finalprojectbe.terms.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "glossary")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "word") //등기 용어
    String title;

    @Column(name = "description") //등기 용어 설명
    String description;

}

