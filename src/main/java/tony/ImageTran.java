package tony;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class ImageTran {

    public void turnColor(String fileLocation,String tranColor,Integer forward) {
        BufferedImage bufferedImage = ImageUtil.addImage(fileLocation);
        if(bufferedImage==null){
            throw new RuntimeException("不是图片文件");
        }
        String suffix=fileLocation.substring(fileLocation.lastIndexOf(".")+1).toLowerCase();;
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        Random random=new Random();
        Integer[] randomArray=new Integer[100];
        for (int i=0;i<100;i++) {
            randomArray[i]=random.nextInt(255);
        }
        for (int j1 = bufferedImage.getMinY(); j1 < height; j1++) {
            for (int j2 = bufferedImage.getMinX(); j2 < width; j2++) {
                int color = bufferedImage.getRGB(j2, j1);
                int red = color & 0xff;
                int green = (color & 0xff00) >> 8;
                int blue = (color & 0xff0000) >> 16;;
                int alpha = (color & 0xff000000) >>> 24;
                int newColor = color;
                if(("red").equalsIgnoreCase(tranColor)){
                    red=255;//红色打满
                }else if(("blue").equalsIgnoreCase(tranColor)){
                    blue=255;
                }else if (("green").equalsIgnoreCase(tranColor)){
                    green=255;
                }else{
                        double yui = red * 0.299 + green * 0.587 + blue * 0.114;
                        if (yui >= 240) {
                            red=10;
                            blue=20;
                            green=30;
                        }else if (yui >= 210) {//很明亮
                            red=40;
                            blue=50;
                            green=60;
                        }else if (yui >= 192) {
                            red=70;
                            blue=80;
                            green=90;
                        }else if (yui >= 152) {
                            if (j2 % 4 == 0) {
                                red -= randomArray[j2 % 100];
                            } else if (j2 % 4 == 1) {
                                green -= randomArray[j2 % 100];
                            } else if (j2 % 4 == 2) {
                                blue -= randomArray[j2 % 100];
                            }
                        }else {
                            if(j2%10==0) {
                                red += randomArray[j2 % 100];
                                green += randomArray[j2 % 100];
                                blue += randomArray[j2 % 100];
                                alpha += randomArray[j2 % 100];
                            }
                        }
                }
                newColor = ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 0);
                bufferedImage.setRGB(j2, j1, newColor);
            }
        }
        try {
            String newFileName=fileLocation.substring(0,fileLocation.lastIndexOf("/"));
            newFileName=newFileName+fileLocation.substring(fileLocation.lastIndexOf("/"),fileLocation.lastIndexOf("."));
            newFileName=newFileName+"_"+tranColor+"."+suffix;
            ImageIO.write(bufferedImage, suffix, new File(newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请选择操作：1.变色");
        String option=in.nextLine();
        if(option==null||(!option.equals("1") && !option.equals("2") && !option.equals("3"))){
            System.out.println("输入错误 请输入数字1-3");
            System.exit(-1);
        }
        if(option.equals("1")) {
            System.out.println("输入图片地址(绝对路径，统一使用 / 格式，支持jpg/gif/png)");
            String png = in.nextLine();
            System.out.println("输入要劣化的颜色(red,blue,green)，输入其它选择则进行随机操作");
            String color = in.nextLine();
            ImageTran tran = new ImageTran();
            tran.turnColor(png,color,1);
            System.out.println("变色文件已生成到同目录");
        }
    }
}
