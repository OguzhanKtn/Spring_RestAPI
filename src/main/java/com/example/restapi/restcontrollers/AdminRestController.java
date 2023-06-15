package com.example.restapi.restcontrollers;

import com.example.restapi.configs.Rest;
import com.example.restapi.models.Admin;
import com.example.restapi.services.AdminService;
import com.example.restapi.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {

    final AdminService adminService;
    final TinkEncDec tinkEncDec;
    final HttpServletRequest request;

   @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody Admin admin){
      if(adminService.login(admin) != null){
          request.getSession().setAttribute("user",admin.getEmail());
      }
      return adminService.login(admin);
   }

   @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Admin admin){
       String chipherText = tinkEncDec.encrypt(admin.getPassword());
       admin.setPassword(chipherText);
       return adminService.save(admin);
   }
}
