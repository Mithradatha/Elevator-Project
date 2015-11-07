/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PassangerPanel extends JPanel {

    private static final long serialVersionUID = 7112021747431420309L;
    
    private int x;
    private int y;

    private MultiplePersonElevator elevator;

    public PassangerPanel (MultiplePersonElevator elevator) {
        this.elevator = elevator;
        this.x= elevator.X_PIXELS;
        this.y = elevator.Y_PIXELS;

        setPreferredSize(new Dimension((x - (x / 4)) / 2, y));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Random rnd = new Random();
        int nPassangers = rnd.nextInt(10);

        int nFloors = elevator.floors;

        int width = getWidth();
        int height = (getHeight() / nFloors);
        //int passanger_width = width / nPassangers;
        //int passanger_height = height / 2;

        //Image img = new ImageIcon("C:\\Users\\Sam\\cse1002\\Eclipse\\ElevatorProject\\src\\project\\stick_figure.png").getImage();
        g.setColor(Color.BLACK);

        for (int i = nFloors; i > 0; i--) {
            g.drawLine(0, i * height, width, i * height);
            //int y_origin = Math.abs(getHeight() - (i * height));
            //int x_origin = width - passanger_width;
            for (int j = 0; j < nPassangers; j++) { 
                //g.drawImage(img, x_origin, y_origin, passanger_width, passanger_height, this);
               // x_origin -= passanger_width;
            }
        }  


    }
}
