/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer.Application;

import imageviewer.Model.Image;
import imageviewer.View.ImageReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FileImageReader implements ImageReader{
    
    private final File[] files;
    private String[] extensions = {".jpg",".png",".gif"};

    public FileImageReader(String path) {
        this(new File(path));
    }
    
    public FileImageReader(File folder) {
        this.files = folder.listFiles(imageType());
    }
    
    @Override
    public Image read() {
        return imageAt(0);
    }

    private FilenameFilter imageType() {
        return new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                for (String extension : extensions) {
                    if(name.toLowerCase().endsWith(extension)) return true;
                }
                return false;
            }
        };
    }

    private Image imageAt(final int index) {
        return new Image() {
            @Override
            public <T> T bitmap() {
                try {
                    return  (T) ImageIO.read(files[index]);
                } catch (IOException ex) {
                    return null;
                }
            }

            @Override
            public Image next() {
                return imageAt((index < files.length-1) ? index+1 : 0);
            }

            @Override
            public Image prev() {
                return imageAt(((index == 0) ? files.length-1 : index-1 ));
            }
        };
    }
    
}
