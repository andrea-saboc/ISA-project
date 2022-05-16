package com.example.isa.service.impl;

import com.example.isa.dto.ImageDto;
import com.example.isa.model.Adventure;
import com.example.isa.service.ImageService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
   /* public ArrayList<ImageDto> getImageForAdventure(Adventure adventure) throws IOException {
        ArrayList<EntityImageDTO> images = new ArrayList<EntityImageDTO>();
        Instructor instructor = adventure.getInstructor();
        String path = new File("./static").getCanonicalPath() + File.separator + "EntityImages" + File.separator +
                "Instructors" + File.separator + instructor.getEmail() + File.separator + adventure.getName().replace(" ", "_");
        for (EntityImage img: adventure.getImages()) {
            String imgstr = "";
            // images.add(new EntityImageDTO(img.getName(), imgstr));
        }
        return images;

    return entityImages;
    }
    }*/
   public byte[] getEntityImage(String imgName){
       BufferedImage bufferedImage = readImage(imgName);
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

    public byte[] getImageByte(String role, String user, String entity, String imgName){
        String path = "public" + File.separator + "images" + File.separator + role + File.separator + user + File.separator + entity + File.separator + imgName;
        ClassPathResource imgFile = new ClassPathResource(path);

        System.out.println("ENTITY IMAGE " + path);

        try {
            return StreamUtils.copyToByteArray(imgFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private BufferedImage readImage( String imgName){
        String path = "main" + File.separator + "resources" + File.separator + "public";
        String imgPath = "images" + File.separator + imgName;

        File f = null;
        try {
            f = new File(new File("./src").getCanonicalPath() + File.separator + path + File.separator + imgPath);
            if(!f.exists()){
                System.out.println("ERROR image does not exists!");
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
