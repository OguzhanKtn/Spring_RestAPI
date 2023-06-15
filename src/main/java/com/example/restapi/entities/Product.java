package com.example.restapi.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;


    @Size(min = 2, max = 100)
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String title;


    @NotEmpty
    @NotNull
    @Size(min = 5, max = 500)
    private String detail;

    @Max(999999)
    @Min(1)
    @NotNull
    private Integer price;
    private Boolean status;
}
