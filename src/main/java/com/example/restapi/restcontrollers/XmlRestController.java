package com.example.restapi.restcontrollers;

import com.example.restapi.models.Catalog;
import com.example.restapi.models.Currency;
import com.example.restapi.models.Plant;
import com.example.restapi.services.XmlRead;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xml")
public class XmlRestController {

    final XmlRead xmlRead;

    @GetMapping("/read")
    public List<Currency> read(){
      List<Currency> ls = xmlRead.result();
        return ls;
    }

    @GetMapping("/catalog")
    public List<Catalog> c_read() throws IOException {
        List<Catalog> ls = xmlRead.catalog_result();
        return ls;
    }

    @GetMapping("/plant")
    public List<Plant> p_read() throws IOException {
        List<Plant> ls = xmlRead.plant_result();
        return ls;
    }
}
