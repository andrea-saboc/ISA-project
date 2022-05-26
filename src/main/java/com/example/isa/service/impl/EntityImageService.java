package com.example.isa.service.impl;


import com.example.isa.model.EntityImage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class EntityImageService {

    public ArrayList<EntityImage> createAndSaveImages(String role, String owner, String name, Set<String> images){
        ArrayList<EntityImage> entityImages = new ArrayList<EntityImage>();

        String path = "main" + File.separator + "resources" + File.separator + "public";
        String dirName = name.replace(' ', '_');
        String imgPath = "images"+File.separator + dirName;
        long numberOfImages = 0;

        File dir = null;
        try {
            dir = new File(new File("./src").getCanonicalPath() + File.separator + path + File.separator + imgPath);
            if(!dir.exists()){
                if(dir.mkdir()){
                    System.out.println("Succesfuly created new directory for new entity");
                }
            }
            else{
                System.out.println("Counting!!!!******");
                numberOfImages = dir.list().length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String img : images) {
            numberOfImages++;
            String imageString = img.split(",")[1];

            BufferedImage image = null;
            byte[] imageByte;

            imageByte = Base64.getDecoder().decode(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            try {
                image = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String imgName = "img" + numberOfImages + ".jpg";

            try {
                System.out.println("Path to save image: " + dir.getCanonicalPath() + File.separator + imgName);
                ImageIO.write(image, "jpg", new File(dir.getCanonicalPath() + File.separator + imgName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            entityImages.add(new EntityImage(imgName, (imgPath + File.separator + imgName).replace('\\','/' )));
        }

        return entityImages;
    }
    public byte[] getEntityImage(String entity, String imgName){
        BufferedImage bufferedImage = readImage(entity, imgName);
        String imgStr = "";
        if(bufferedImage != null){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            try {
                ImageIO.write(bufferedImage, "jpg", bos);
                byte[] imageBytes = bos.toByteArray();
                bos.close();
                return imageBytes;
                //imgStr = Base64.getEncoder().encodeToString(imageBytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private BufferedImage readImage( String entity, String imgName){
        String path = "main" + File.separator + "resources" + File.separator + "public";
        String imgPath = "images" + File.separator + entity + File.separator + imgName;

        File f = null;
        try {
            f = new File(new File("./src").getCanonicalPath() + File.separator + path + File.separator + imgPath);
            if(!f.exists()){
                System.out.println("ERROR img ne postoji");
            }else{
                // file postoji sada treba da ucitamo sliku
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                    return img;
                } catch (IOException e) {

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
