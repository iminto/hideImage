package tony;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLConnection;
import java.text.DecimalFormat;

/**
 * @Author 白菜不是菜 chenwen2@wdai.com
 * @Version V1.0
 * @Description
 * @Date 19-3-21 下午12:52
 **/
public class ImageUtil {

    public static boolean validPng(String fileLocation) {
        String suffix = fileLocation.substring(fileLocation.lastIndexOf(".") + 1);
        if (!("png").equals(suffix.toLowerCase())) {
            //JPEG是有损格式,保存JPEG并重新加载后颜色将不会完全相同，GIF比较复杂暂不考虑
            return false;
        }
        File file = new File(fileLocation);
        InputStream is = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            is = new BufferedInputStream(fileInputStream);
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            return ("image/png").equals(mimeType);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileInputStream.close();
                is.close();
            } catch (IOException e) {
            }
        }
    }


    public static boolean isWin() {
        String osName = System.getProperty("os.name");
        return osName != null && osName.startsWith("Windows");
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !("").equals(str);
    }

    public static String formatFileSize(long size) {
        String hrSize = null;
        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");
        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }
        return hrSize;
    }

    public static int[] getAutoSize(int width, int height, int maxWidth, int maxHeight) {
        if (width <= 0 || height <= 0 || maxHeight <= 0 || maxWidth <= 0) {
            return new int[]{0, 0};
        }
        if (width <= maxWidth && height <= maxHeight) {
            int[] size = new int[]{width, height};
            return size;
        } else {
            int newWidth = 0;
            int newHeight = 0;
            if (width > maxWidth && height > maxHeight) {
                double ratio = (double) maxWidth / width;
                newWidth = Double.valueOf(width * ratio).intValue();
                if (Double.valueOf(height * ratio).intValue() < maxHeight) {
                    newHeight = Double.valueOf(height * ratio).intValue();
                } else {
                    newHeight = maxHeight;
                }
            } else if (width > maxWidth) {
                newWidth = maxWidth;
                double ratio = (double) maxWidth / width;
                newHeight = Double.valueOf(height * ratio).intValue();
            } else if (height > maxHeight) {
                newHeight = maxHeight;
                double ratio = (double) maxHeight / height;
                newWidth = Double.valueOf(width * ratio).intValue();
            }
            int[] size = new int[]{newWidth, newHeight};
            return size;
        }
    }

    public static BufferedImage addImage(String fileLocation) {
        if (fileLocation == null || fileLocation.equals("")) {
            throw new RuntimeException("文件名不能为空");
        }
        String suffix = fileLocation.substring(fileLocation.lastIndexOf(".") + 1).toLowerCase();
        if (!("png").equals(suffix) && !("gif").equals(suffix)
                && !("jpeg").equals(suffix) && !("jpg").equals(suffix) && !("bmp").equals(suffix)) {
            return null;
        }
        BufferedImage bufferedImage = null;
        InputStream is = null;
        FileInputStream fileInputStream = null;
        try {
            File file = new File(fileLocation);
            fileInputStream = new FileInputStream(file);
            is = new BufferedInputStream(fileInputStream);
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            if(mimeType.equals("image/gif")||mimeType.equals("image/x-bitmap")||
                    mimeType.equals("image/png")||mimeType.equals("image/jpeg")){
                bufferedImage = ImageIO.read(file);
                return bufferedImage;
            }else {
                throw new RuntimeException("MIME不是图片文件");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                is.close();
            } catch (IOException e) {
            }
            return bufferedImage;
        }
    }
}
