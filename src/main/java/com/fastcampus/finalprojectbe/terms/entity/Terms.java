package com.fastcampus.finalprojectbe.terms.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "terms")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Terms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    public Terms(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
