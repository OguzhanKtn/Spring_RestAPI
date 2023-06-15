package com.example.restapi.restcontrollers;

import com.example.restapi.models.MusicCategoryList;
import com.example.restapi.services.MozartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mozart")
public class MozartRestController {

    final MozartService mozartService;

    @GetMapping("/service")
    public MusicCategoryList service(){
     return mozartService.result();
    }
}
