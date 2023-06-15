package com.example.restapi.services;

import com.example.restapi.configs.Rest;
import com.example.restapi.models.Admin;
import com.example.restapi.repositories.AdminRepository;
import com.google.gson.stream.JsonToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    final HttpServletRequest request;
    final AdminRepository adminRepository;
    final TinkEncDec tinkEncDec;
    public ResponseEntity login(Admin admin){
        List<Admin> admins =  adminRepository.findAll();

           for(Admin admin1 : admins){
               String plainText = tinkEncDec.decrypt(admin1.getPassword());
               if(admin.getPassword().equals(plainText) && admin.getEmail().equals(admin1.getEmail())){
                   Rest rest = new Rest(true,admin);
                   return new ResponseEntity(rest, HttpStatus.OK);
               }else{
                   Rest rest = new Rest(false,"Hatalı Deneme !");
                   return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
               }
           }
            return null;
       }

    public ResponseEntity save(Admin admin){
        try{
            adminRepository.save(admin);
            Rest rest = new Rest(true,admin);
            return new ResponseEntity(rest, HttpStatus.OK);
        }catch (Exception ex){
            Rest rest = new Rest(false,ex.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }
    }
}
