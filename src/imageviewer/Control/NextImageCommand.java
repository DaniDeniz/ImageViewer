/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageviewer.Control;

import imageviewer.View.ImageDisplay;

/**
 *
 * @author Dani
 */
public class NextImageCommand implements Command{
    
    private final ImageDisplay imageDisplay;

    public NextImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }
    
    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.image().next());
    }
    
}
