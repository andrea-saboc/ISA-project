package com.example.isa.controller;

import com.example.isa.service.ImageService;
import com.example.isa.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/entityImage")
public class ImageController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/images/{imgName}", method = GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageRes( @PathVariable String imgName) throws IOException {

        //byte[] img = entityImageService.getImageByte(role, user, entity, imgName);
        System.out.println("Trazenje slike "+imgName);
        byte[] img = imageService.getEntityImage(imgName);
        if(img != null) return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(img);
        else{
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.IMAGE_JPEG).body(null);
        }

    }
}

