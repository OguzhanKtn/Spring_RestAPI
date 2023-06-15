package com.example.restapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Catalog {
    private String title;
    private String artist;
    private String country;
    private String company;
    private String price;
    private String year;

}
