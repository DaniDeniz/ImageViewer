package imageviewer.Model;

public interface Image {

    <T> T bitmap();
    Image next();
    Image prev();
}
