package util;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.image.BufferedImage;

public class CustomTools {
    public static JLabel loadImage(String resource){
        BufferedImage image;
        try{
            InputStream inputStream = CustomTools.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }
}
