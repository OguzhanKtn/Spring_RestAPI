package com.example.restapi.services;

import com.example.restapi.configs.Rest;
import com.example.restapi.entities.Product;
import com.example.restapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public ResponseEntity save(Product product){
        try{
            productRepository.save(product);
            Rest rest = new Rest(true,product);
            return new ResponseEntity(rest,HttpStatus.OK);
        }catch (Exception ex){
            Rest rest = new Rest(false,ex.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity list(){
        List<Product> ls = productRepository.findAll();
        Rest rest = new Rest(true,ls);
       return new ResponseEntity(rest, HttpStatus.OK);
    }

    public ResponseEntity delete(Long pid){
        try{
            productRepository.deleteById(pid);
            Rest rest = new Rest(true,pid);
            return new ResponseEntity(rest,HttpStatus.OK);
        }catch (Exception ex){
            Rest rest = new Rest(false,ex.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update(Product product){
        Optional<Product> optionalProduct = productRepository.findById(product.getPid());
        try{
            if(optionalProduct.isPresent()){
                productRepository.saveAndFlush(product);
                Rest rest = new Rest(true,optionalProduct.get());
                return new ResponseEntity(rest,HttpStatus.OK);
            }
        }catch (Exception ex){
            Rest rest = new Rest(false,ex.getMessage());
            return new ResponseEntity(rest,HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
