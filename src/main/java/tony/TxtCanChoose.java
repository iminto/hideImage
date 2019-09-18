package tony;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TxtCanChoose extends FileFilter{
    @Override
    public boolean accept(File pathname) {
        String name=pathname.getName();
        return name.toLowerCase().endsWith(".txt")||name.toLowerCase().endsWith(".java")||
                name.toLowerCase().endsWith(".ini")||name.toLowerCase().endsWith(".properties")||
                name.toLowerCase().endsWith(".md")||name.toLowerCase().endsWith(".yml")||
                name.toLowerCase().endsWith(".html")||name.toLowerCase().endsWith(".htm")||
                name.toLowerCase().endsWith(".xml")||name.toLowerCase().endsWith(".csv")||name.toLowerCase().endsWith(".sh");
    }

    @Override
    public String getDescription() {
        return "*.文本";
    }
    
    
}
