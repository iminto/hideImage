package tony;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TxtCanChoose extends FileFilter{
    @Override
    public boolean accept(File pathname) {
        String name=pathname.getName();
        return name.toLowerCase().endsWith(".txt");
    }

    @Override
    public String getDescription() {
        return "*.txt文本";
    }
    
    
}
