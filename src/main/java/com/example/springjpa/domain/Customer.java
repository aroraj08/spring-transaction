package com.example.springjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name="CUSTOMER_ID_SEQ", initialValue = 1)
public class Customer {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID_SEQ")
    private Long id;

    @Column(unique = true, updatable = false)
    private Long customerId; // business key
    private String firstName;
    private String lastName;

    /*@OneToOne(fetch = FetchType.LAZY, optional = true)
    private Address address;*/

}
