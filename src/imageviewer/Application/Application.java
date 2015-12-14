package imageviewer.Application;

import imageviewer.Control.Command;
import imageviewer.Control.NextImageCommand;
import imageviewer.Control.PrevImageCommand;
import imageviewer.Model.Image;
import imageviewer.View.ImageDisplay;
import imageviewer.View.ImageReader;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Application extends JFrame{

    private HashMap<String,Command> commands = new HashMap<>();
    private ImageDisplay imageDisplay;
    
    public static void main(String[] args) {
        new Application().setVisible(true);
    }

    public Application() {
        deployUI();
        initCommands();
    }

    private void deployUI() {
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(components());
        this.setSize(new Dimension(500, 500));
    }

    private JPanel components() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imagePanel(), BorderLayout.CENTER);
        panel.add(toolbar(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel imagePanel() {
        ImagePanel panel = new ImagePanel(readImage());
        panel.addMouseListener(changeImageWithMouse());
        imageDisplay = panel;
        return panel;
    }

    private Image readImage() {
        ImageReader reader = new FileImageReader("C:\\Users\\Public\\Pictures\\Sample Pictures");
        return reader.read();
   
    }

    private JPanel toolbar() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(prevButton());
        panel.add(nextButton());
        return panel;
        
    }
    
    private JButton nextButton(){
        JButton button = new JButton(">");
        button.addActionListener(command("next"));
        return button;
    }

    private JButton prevButton() {
        JButton button = new JButton("<");
        button.addActionListener(command("prev"));
        return button;        
    }
    
    private ActionListener command(String command){
        return new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(command).execute();
            }            
        };                
    }

    private void initCommands() {
        commands.put("next", new NextImageCommand(imageDisplay));
        commands.put("prev", new PrevImageCommand(imageDisplay));
    }

    private MouseListener changeImageWithMouse() {
        return new MouseListener() {
            private int inicio;
            private int diference;
            
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                inicio = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                diference = e.getX()-inicio;
                if(diference > 0){
                    commands.get("prev").execute();
                } else {
                    commands.get("next").execute();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
    }

    
    
}