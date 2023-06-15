package com.example.restapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plant {

    private String common;
    private String botanical;
    private String zone;
    private String light;
    private String price;
    private String availability;
}
