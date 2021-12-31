package com.example.isa.model;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Entity
@Table(name = "Images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Integer imgId;
    private String img;
    private String imgPath;
    private byte[] imageByte;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "boat_id")
    private Boat boat;

    public void saveImage() {
        String imageString = img.split(",")[1];
        BufferedImage imageDone = null;
        byte[] imageByte;
        imageByte = Base64.getDecoder().decode(imageString);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        try {
            imageDone = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imagePathName= "Images"+File.separator+"Boats"+File.separator+ imgId + ".png";

        try {
            File outputfile = new File(new File("."+File.separator+"src").getCanonicalPath()+File.separator+imagePathName);
            ImageIO.write(imageDone, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgPath = imagePathName;
        img = imagePathName;

        }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_exter_id", referencedColumnName = "id", nullable = true)
    private Boat boatExter;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "boat_inter_id", referencedColumnName = "id", nullable = true)
    private Boat boatInter;

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Image(){}

    public Image(String img){

        this.img = img;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Boat getBoatExter() {
        return boatExter;
    }

    public void setBoatExter(Boat boatExter) {
        this.boatExter = boatExter;
    }

    public Boat getBoatInter() {
        return boatInter;
    }

    public void setBoatInter(Boat boatInter) {
        this.boatInter = boatInter;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
