package com.example.isa.controller;

import com.example.isa.service.impl.EntityImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController

public class EntityImageController {

    @Autowired
    private EntityImageService entityImageService;


    @RequestMapping(value = "/entityImage/images/{entity}/{imgName}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> getImageRes(@PathVariable String entity, @PathVariable String imgName) throws IOException {

        //byte[] img = entityImageService.getImageByte(role, user, entity, imgName);
        byte[] img = entityImageService.getEntityImage(entity, imgName);
        if(img != null){
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(img);
        }else{
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.IMAGE_JPEG).body(null);
        }


    }
}