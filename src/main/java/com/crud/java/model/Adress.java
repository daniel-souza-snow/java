package com.crud.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{msg.street-name.required}")
    private String streetName;

    @NotNull(message = "{msg.number.required}")
    private Integer number;

    private String complement;

    @NotEmpty(message = "{msg.neighbourhood-required}")
    private String neighbourhood;

    @NotEmpty(message = "{msg.city.required}")
    private String city;

    @NotEmpty(message = "{msg.state.required}")
    private String state;

    @NotEmpty(message = "{msg.country.required}")
    private String country;

    @NotEmpty(message = "{msg.zipcode.required}")
    private String zipcode;

    private String latitude;
    private String longitude;
}
