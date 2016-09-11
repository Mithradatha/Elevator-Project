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
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PassengerPanel extends JPanel {

    private static final long serialVersionUID = 7112021747431420309L;
    
    private int x;
    private int y;

    private Elevator elevator;

    private ArrayList<PassengerRequest>[] floorPassengers;

    public PassengerPanel (Elevator elevator, int X_PIXELS, int Y_PIXELS, ArrayList<PassengerRequest>[] floorPassengers) {
        this.elevator = elevator;
        this.x= X_PIXELS;
        this.y = Y_PIXELS;
        this.floorPassengers = floorPassengers;


        setPreferredSize(new Dimension((x - (x / 4)) / 2, y));
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        int nFloors = elevator.floors;

        int width = getWidth();
        int height = (getHeight() / elevator.floors);

        g.setColor(Color.BLACK);

        for (int i = nFloors; i > 0; i--) {
            g.drawLine(0, i * height, width, i * height);
        }
        
        int passenger_width = getWidth() / 5;
        int passenger_height = getHeight() / elevator.floors;
        
        Image img = new ImageIcon("C:\\Users\\Sam\\cse1002\\Eclipse\\ElevatorProject\\src\\project\\stick_figure.png").getImage();
        
        
        for (int i = 1; i < floorPassengers.length; i++) {
            int x_origin = width - passenger_width;
            int y_origin = getHeight() - (i * height);
            for (int j = 0; j < floorPassengers[i].size(); j++) {
                g.drawImage(img, x_origin, y_origin, passenger_width, passenger_height, this);
                x_origin -= passenger_width;
            }
        }
    }
}
