package com.fastcampus.finalprojectbe.openapi.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address_code")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "legdong_code")
    private String legdongCode;

    @Column(name = "legdong_name")
    private String legdongName;
    @Column(name = "is_used")
    private String isUsed;

}
