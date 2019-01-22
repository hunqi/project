package cn.rs.picwall.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageUtil {

    public static byte[] resize(byte[] image, int expectedSize) {
        System.out.println("origin.size=" + image.length);

        BufferedImage originImage = getImage(image);
        if (originImage == null) return null;

        int width = originImage.getWidth();
        int height = originImage.getHeight();

        BigDecimal d1 = BigDecimal.valueOf(width * height);
        BigDecimal d2 = BigDecimal.valueOf(expectedSize);

        BigDecimal rate = BigDecimalUtil.sqrt(d2.divide(d1, MathContext.DECIMAL64));
        width = rate.multiply(BigDecimal.valueOf(width)).intValue();
        height = rate.multiply(BigDecimal.valueOf(height)).intValue();

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        newImage.getGraphics().drawImage(originImage, 0, 0, width, height, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(newImage, "", out);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(newImage);

            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage getImage(byte[] imageData) {
        try {
            return ImageIO.read(new ByteArrayInputStream(imageData));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public static void main(String[] args) throws IOException {
        String basePath = "C:\\Users\\Public\\Pictures\\Sample Pictures";
        String fileName = "newChrysanthemum.jpg";

        try (InputStream in = Files.newInputStream(Paths.get(basePath, fileName))) {

            byte[] buf = new byte[48];
            int b = -1;

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while ((b = in.read(buf)) != -1) {
                out.write(buf, 0, b);
            }
            out.flush();

            byte[] result = resize(out.toByteArray(), 512 * 1024);
            try (OutputStream out2 = Files.newOutputStream(Paths.get(basePath, "new" + fileName))){
                out2.write(result);
            }

            out.close();
        }
    }*/

}
