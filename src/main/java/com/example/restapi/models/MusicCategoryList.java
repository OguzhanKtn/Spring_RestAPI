package com.example.restapi.models;

import lombok.Data;

import java.util.List;

@Data
public class MusicCategoryList {

    private List<MusicCategory> musicCategories;
}
