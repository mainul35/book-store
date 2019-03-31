package com.book.util;

import com.book.entity.Attachment;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Component
public class ImageUtil {

    @Autowired
    private ServletContext servletContext;

    private static String [] afterConvertedExtensions = {
            ".jpg",
//                ".png",
//                ".gif"
    };
    private static int[][] sizes = {
            {100, 100},
            {128, 144},
            {240, 320},
            {320, 480},
    };

    /**
     *
     * @param bufferedImage Image file to write to disk
     * @param fileName File name to be saved as
     *
     * */
    private static void writeImageToFile (Image bufferedImage, String fileName) throws IOException {
        ImageIO.write(toBufferedImage(bufferedImage), "jpg", new File(fileName));
    }

    /**
     * @param img Image that has been uploaded
     * @return BufferedImage object that has been generated as thumb
     * */
    private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = (Graphics2D) bimage.getGraphics();
        bGr.rotate(Math.toRadians(0), bimage.getWidth() / 2, bimage.getHeight() / 2);
        bGr.drawImage(img, 0, 0, null);

        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    /**
     * @param attachment Attachment that
     * */
    public static void generateThumbs (Attachment attachment, File file) throws IOException {
        for (int[] size : sizes) {
            for (String afterGenerateExtension : afterConvertedExtensions) {
                Image img = ImageIO.read(file).getScaledInstance(size[0], size[1], BufferedImage.SCALE_SMOOTH);
                writeImageToFile(img, attachment.getPath()+attachment.getDisplayName()+"_"+size[0]+"_"+size[1]+afterGenerateExtension);
            }
        }
    }
    public byte[] readImage(Attachment attachment, int size) throws IOException {
        InputStream in = null;
        String filePath = "";
        for (int[] widthAndHeight : sizes) {
            if (widthAndHeight[0] == size) {
                filePath = attachment.getPath()+attachment.getDisplayName()+"_"+widthAndHeight[0]+"_"+widthAndHeight[1]+attachment.getType();
            }
        }
        if (filePath == "") {
            filePath = attachment.getPath()+attachment.getDisplayName()+"_128_144"+attachment.getType();
        }
        if (servletContext.getResourceAsStream(filePath) == null) {
            in = new FileInputStream(new File(filePath));
        } else {
            in = servletContext.getResourceAsStream(filePath);
        }
        return IOUtils.toByteArray(in);
    }


}
