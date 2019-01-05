package DataLink;

import javax.swing.*;
import java.awt.*;

public class MainJFrame extends JFrame {
    public static Link link;

    public MainJFrame(){
        setBounds(100, 100, 500,600);
        setTitle("工人");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        link = new Link();
        init();
        setVisible(true);
    }

    public void init(){
        add(new AddWorkerPanel(), BorderLayout.NORTH);
        add(new ListWorkersPanel(), BorderLayout.CENTER);
    }

    public static void main(String[] args){
        MainJFrame mainJFrame = new MainJFrame();
    }

}
