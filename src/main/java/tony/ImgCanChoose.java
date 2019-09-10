package tony;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImgCanChoose extends FileFilter{

    @Override
    public boolean accept(File pathname) {
        String name=pathname.getName();
        return name.toLowerCase().endsWith(".png");
    }
    
    @Override
    public String getDescription() {
        return "*.PNG图片";
    }
}