package tony;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @Author 白菜不是菜
 * @Version V1.0 *
 * @Description *
 * @Date 19-3-20 下午2:33
 **/
public class ImageHide {

    private int enabled=0;//可处理的最大字节数

    private String fileLocation="";//图片地址

    private String hideStr;//要隐写的文字

    private String binStr;

    private int height;

    private int width;

    private String newFileName="";

    private String findStr="";

    private BufferedImage addPng(String fileLocation){
        if(fileLocation==null ||fileLocation.equals("")){
            throw new RuntimeException("文件名不能为空");
        }
        this.fileLocation=fileLocation;
        BufferedImage bufferedImage = null;
        if(!ImageUtil.validPng(fileLocation)){
            //JPEG是有损格式,保存JPEG并重新加载后颜色将不会完全相同，GIF比较复杂暂不考虑
            throw new RuntimeException("目前仅支持PNG格式");
        }
        try {
            File file = new File(fileLocation);
            bufferedImage = ImageIO.read(file);
            this.height = bufferedImage.getHeight();
            this.width = bufferedImage.getWidth();
            if(this.height<5||this.width<5){
                throw new RuntimeException("哥，图片像素也太小了吧");
            }
            int pixSum=this.width*this.height;
            int enabled=((pixSum-5)*3)/8;
            this.enabled=enabled;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }

    private String addText(String hideStr){
        if(hideStr==null ||hideStr.equals("")){
            throw new RuntimeException("要隐藏的文字不能为空");
        }
        this.hideStr=hideStr;
        byte[] bytes = StringBinUtil.strToByteArray(hideStr);
        String binStr = StringBinUtil.byteArrayToString(bytes);
        this.binStr=binStr;
        int binStrLength=binStr.length();
        if(binStrLength>=this.enabled*8){
            System.out.println("允许处理的最大字节数是："+enabled+",你目前的字节数是:"+binStrLength/8);
        }
        return binStr;
    }

    private String addFile(String file){
        try {
            String content = new Scanner(new File(file)).useDelimiter("\\Z").next();
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void Hide() {
        BufferedImage bufferedImage = addPng(fileLocation);
        int count = 0;//像素的位置
        int height = this.height;
        int width =this.width;
        int binStrLength=binStr.length();
        String lengthStr=StringBinUtil.intToBinStr(binStrLength);
        int offsetx=0;
        for (int j1 = bufferedImage.getMinY(); j1 < height; j1++) {
            for (int j2 = bufferedImage.getMinX(); j2 < width; j2++) {
                count = (j1 * width + j2) * 3;
                int rgb = bufferedImage.getRGB(j2, j1);
                int value = 0xff000000 | rgb;
                int red = (value >> 16) & 0xFF;
                int green = (value >> 8) & 0xFF;
                int blue = (value >> 0) & 0xFF;
                int alpha = (value >> 24) & 0xff;
                int newColor = rgb;
                if (count < binStr.length() - 2) {
                    int offset = count;
                    if (binStr.charAt(offset) == '1') {
                        if (red % 2 == 0) {
                            red += 1;//如果二进制是1,偶数变奇数,保证1对奇0对偶
                        }
                    } else {//如果二进制是0,奇数变偶数
                        if (red % 2 == 1) {
                            red -= 1;
                        }
                    }
                    offset += 1;
                    if (binStr.charAt(offset) == '1') {
                        if (green % 2 == 0) {
                            green += 1;
                        }
                    } else {
                        if (green % 2 == 1) {
                            green -= 1;
                        }
                    }
                    offset += 1;
                    if (binStr.charAt(offset) == '1') {
                        if (blue % 2 == 0) {
                            blue += 1;
                        }
                    } else {
                        if (blue % 2 == 1) {
                            blue -= 1;
                        }
                    }
                    newColor = ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 0);
                }

                if((j1==height-1)&&(j2+5>=width)){//to note the length
                    if (lengthStr.charAt(offsetx) == '1') {
                        if (red % 2 == 0) {
                            red += 1;//如果二进制是1,偶数变奇数,保证1对奇0对偶
                        }
                    } else {//如果二进制是0,奇数变偶数
                        if (red % 2 == 1) {
                            red -= 1;
                        }
                    }
                    offsetx += 1;
                    if (lengthStr.charAt(offsetx) == '1') {
                        if (green % 2 == 0) {
                            green += 1;
                        }
                    } else {
                        if (green % 2 == 1) {
                            green -= 1;
                        }
                    }
                    offsetx += 1;
                    if (lengthStr.charAt(offsetx) == '1') {
                        if (blue % 2 == 0) {
                            blue += 1;
                        }
                    } else {
                        if (blue % 2 == 1) {
                            blue -= 1;
                        }
                    }
                    offsetx += 1;
                    newColor = ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | ((blue & 0xFF) << 0);
                }
                bufferedImage.setRGB(j2, j1, newColor);
            }
        }
        try {
            String newFileName=this.fileLocation.substring(0,fileLocation.lastIndexOf("/"));
            newFileName=newFileName+this.fileLocation.substring(fileLocation.lastIndexOf("/"),fileLocation.lastIndexOf("."));
            this.newFileName=newFileName+"_hide.png";
            ImageIO.write(bufferedImage, "png", new File(this.newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String ImageExtract(String fileLocation) {
        if(fileLocation==null ||fileLocation.equals("")){
            throw new RuntimeException("要提取文字的文件名不能为空");
        }
        if(!ImageUtil.validPng(fileLocation)){
            throw new RuntimeException("目前仅支持PNG格式");
        }
        StringBuilder binStr = new StringBuilder();
        StringBuilder lenBinStr = new StringBuilder(15);
        int endLen=0;
        try {
            File file = new File(fileLocation);
            BufferedImage bufferedImage = ImageIO.read(file);
            int count = 0;//像素的位置
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            for(int y=height-1;y<height;y++){
                for (int x=width-5;x<width;x++){
                    int rgb = bufferedImage.getRGB(x, y);
                    int value = 0xff000000 | rgb;
                    int red = (value >> 16) & 0xFF;
                    int green = (value >> 8) & 0xFF;
                    int blue = (value >> 0) & 0xFF;
                    if (red % 2 == 0) {
                        lenBinStr.append(0);
                    } else {
                        lenBinStr.append(1);
                    }
                    if (green % 2 == 0) {
                        lenBinStr.append(0);
                    } else {
                        lenBinStr.append(1);
                    }
                    if (blue % 2 == 0) {
                        lenBinStr.append(0);
                    } else {
                        lenBinStr.append(1);
                    }
                }
            }
            endLen=Integer.parseInt(lenBinStr.toString(),2);
            lableY:
            for (int j1 = bufferedImage.getMinY(); j1 < height; j1++) {
                lableX:
                for (int j2 = bufferedImage.getMinX(); j2 < width; j2++) {
                    count = (j1 * width + j2) * 3;
                    int rgb = bufferedImage.getRGB(j2, j1);
                    int value = 0xff000000 | rgb;
                    int red = (value >> 16) & 0xFF;
                    int green = (value >> 8) & 0xFF;
                    int blue = (value >> 0) & 0xFF;
                    if (count>=endLen) {
                        break lableY;
                    }
                    if (red % 2 == 0) {
                        binStr.append(0);
                    } else {
                        binStr.append(1);
                    }
                    if (green % 2 == 0) {
                        binStr.append(0);
                    } else {
                        binStr.append(1);
                    }
                    if (blue % 2 == 0) {
                        binStr.append(0);
                    } else {
                        binStr.append(1);
                    }
                }
            }
            bufferedImage = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] old = StringBinUtil.binStrToByteArray(binStr.substring(0,endLen));
        this.findStr=new String(old);
        return this.findStr;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请选择操作：1.隐藏文字 2.隱藏文本 3.提取文字");
        String option=in.nextLine();
        if(option==null||(!option.equals("1") && !option.equals("2") && !option.equals("3"))){
            System.out.println("输入错误 请输入数字1-3");
            System.exit(-1);
        }
        if(option.equals("1")) {
            System.out.println("输入png图片地址");
            String png = in.nextLine();
            ImageHide rc = new ImageHide();
            rc.addPng(png);
            System.out.println("输入要隐藏的文本文件,当前图片最大支持" + rc.getEnabled() + "字节");
            rc.addText(in.nextLine());
            rc.Hide();
            System.out.println("隐写文件已生成，文件地址："+rc.getNewFileName());
        }else if(option.equals("2")){
            System.out.println("输入png图片地址");
            String png = in.nextLine();
            ImageHide rc = new ImageHide();
            rc.addPng(png);
            System.out.println("输入要隐藏的文本文件绝对路径,当前图片最大支持" + rc.getEnabled() + "字节");
            String addText=rc.addFile(in.nextLine());
            rc.addText(addText);
            rc.Hide();
            System.out.println("隐写文件已生成，文件地址："+rc.getNewFileName());
        }else{
            System.out.println("输入要提取文字的png图片地址");
            String png = in.nextLine();
            ImageHide rc = new ImageHide();
            String extractStr = rc.ImageExtract(png);
            System.out.println("提取出文字：\n"+extractStr);
        }

    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getHideStr() {
        return hideStr;
    }

    public void setHideStr(String hideStr) {
        this.hideStr = hideStr;
    }

    public String getBinStr() {
        return binStr;
    }

    public void setBinStr(String binStr) {
        this.binStr = binStr;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getNewFileName() {
        return newFileName;
    }

    public void setNewFileName(String newFileName) {
        this.newFileName = newFileName;
    }
}