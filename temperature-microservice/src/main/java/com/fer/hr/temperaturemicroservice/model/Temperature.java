package com.fer.hr.temperaturemicroservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"name", "unit", "value"})  // Specify the order here
public class Temperature {

    @JsonProperty("name")
    @Transient
    private final String name = "temperature";

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unit;

    @Column(name = "\"value\"")
    private Double value;

}
